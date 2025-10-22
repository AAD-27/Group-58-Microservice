package com.bits.ride.hailing.dto;

import lombok.Data;

/**
 * Request DTO used to create or update rider information.
 *
 * <p>Contains the minimal set of fields accepted from clients when creating or
 * updating a rider.
 */
@Data
public class RiderRequestDTO {
    private String name;
    private String email;
    private String phone;
}
