package com.ridehailing.payment.service;

import com.ridehailing.payment.dto.PaymentRequest;
import com.ridehailing.payment.model.Payment;

public interface PaymentService {
    Payment processPayment(PaymentRequest request);
    Payment refundPayment(Long id);
    Payment getPayment(Long id);
}
