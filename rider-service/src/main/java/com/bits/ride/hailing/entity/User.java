package com.bits.ride.hailing.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Simple transfer object representing a user/rider summary.
 *
 * <p>Used by the service layer to return a lightweight user projection that is
 * independent of the JPA entity.
 */
@Data
public class User {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Timestamp createdAt;
}
