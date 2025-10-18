package com.bits.ride.hailing.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "riders")
public class RiderEntity {
    @Id
    @Column(name = "rider_id")
    private Long id;

    private String name;
    private String email;
    private String phone;

    @Column(name = "created_at")
    private Timestamp createdAt;
}

