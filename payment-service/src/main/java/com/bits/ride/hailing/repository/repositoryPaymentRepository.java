package com.ridehailing.payment.repository;

import com.ridehailing.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByExternalTxnId(UUID externalTxnId);
}
