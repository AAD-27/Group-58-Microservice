package com.bits.ride.hailing.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * JPA entity representing a rider stored in the database.
 *
 * <p>Maps to the "riders" table and contains basic contact information and a
 * created timestamp.
 */
@Data
@Entity
@Table(name = "riders")
public class RiderEntity {
    @Id
    @Column(name = "id")
    private Long id;

    private String name;
    private String email;
    private String phone;

    @Column(name = "created_at")
    private Timestamp createdAt;
}
