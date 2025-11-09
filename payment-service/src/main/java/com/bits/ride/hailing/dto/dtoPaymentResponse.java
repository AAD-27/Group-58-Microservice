package com.ridehailing.payment.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentResponse {
    private Long id;
    private UUID externalTxnId;
    private String status;
    private Double amount;
    private LocalDateTime createdAt;

    public PaymentResponse(Long id, UUID externalTxnId, String status, Double amount, LocalDateTime createdAt) {
        this.id = id;
        this.externalTxnId = externalTxnId;
        this.status = status;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    // Getters...
}
