package com.ridehailing.payment.dto;

import jakarta.validation.constraints.*;
import java.util.UUID;

public class PaymentRequest {

    @NotNull
    private UUID externalTxnId;

    @NotNull @Positive
    private Long tripId;

    @NotNull @Positive
    private Long riderId;

    @NotNull @Positive
    private Double amount;

    @NotBlank
    private String paymentMethod;

    // Getters and setters...
}
