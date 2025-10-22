package com.bits.ride.hailing.repository;

import com.bits.ride.hailing.entity.RiderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository providing CRUD access to rider entities.
 *
 * Extends Spring Data JPA's {@link JpaRepository} to inherit standard
 * data access methods for {@link RiderEntity}.
 */
@Repository
public interface RiderRepository extends JpaRepository<RiderEntity, Long> {
}
