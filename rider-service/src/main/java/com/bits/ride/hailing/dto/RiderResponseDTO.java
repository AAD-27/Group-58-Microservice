package com.bits.ride.hailing.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class RiderResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Timestamp createdAt;
}

