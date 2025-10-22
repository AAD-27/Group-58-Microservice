package com.bits.ride.hailing.ridersvc.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Response DTO used internally by the ridersvc package to represent rider data.
 *
 * <p>This is a plain POJO with JavaBean getters and setters to avoid Lombok
 * dependencies in some internal modules.
 */
@Data
public class RiderResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Timestamp createdAt;

}
