package com.bits.ride.hailing.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Timestamp createdAt;
}
