package com.ridehailing.payment.service.impl;

import com.ridehailing.payment.dto.PaymentRequest;
import com.ridehailing.payment.model.Payment;
import com.ridehailing.payment.repository.PaymentRepository;
import com.ridehailing.payment.service.PaymentService;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private final PaymentRepository paymentRepository;
    private final MeterRegistry meterRegistry;
    private final Random rng = new Random();

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, MeterRegistry meterRegistry) {
        this.paymentRepository = paymentRepository;
        this.meterRegistry = meterRegistry;
    }

    @Override
    @Transactional
    public Payment processPayment(PaymentRequest request) {
        UUID txn = request.getExternalTxnId();
        Optional<Payment> existing = paymentRepository.findByExternalTxnId(txn);
        if (existing.isPresent()) {
            log.info("Idempotent call detected for txnId={}", txn);
            meterRegistry.counter("payments.idempotent.calls").increment();
            return existing.get();
        }

        Payment payment = new Payment();
        payment.setExternalTxnId(txn);
        payment.setTripId(request.getTripId());
        payment.setRiderId(request.getRiderId());
        payment.setAmount(request.getAmount());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setStatus(Payment.PaymentStatus.PENDING);

        boolean success = rng.nextInt(100) < 90;
        payment.setStatus(success ? Payment.PaymentStatus.SUCCESS : Payment.PaymentStatus.FAILED);

        try {
            Payment saved = paymentRepository.saveAndFlush(payment);
            log.info("Processed payment id={} txn={} status={}", saved.getId(), txn, saved.getStatus());
            meterRegistry.counter("payments.processed", "status", saved.getStatus().name()).increment();
            return saved;
        } catch (DataIntegrityViolationException e) {
            log.warn("Duplicate txnId={} detected, returning existing payment.", txn);
            return paymentRepository.findByExternalTxnId(txn).orElseThrow();
        }
    }

    @Override
    @Transactional
    public Payment refundPayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        if (payment.getStatus() == Payment.PaymentStatus.REFUNDED)
            return payment;

        payment.setStatus(Payment.PaymentStatus.REFUNDED);
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPayment(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }
}
