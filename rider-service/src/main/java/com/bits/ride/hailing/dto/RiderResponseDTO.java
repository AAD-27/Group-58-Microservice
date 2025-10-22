package com.bits.ride.hailing.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Response DTO representing a rider returned by service endpoints.
 *
 * <p>Contains basic contact and metadata fields suitable for API responses.
 */
@Data
public class RiderResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Timestamp createdAt;
}
