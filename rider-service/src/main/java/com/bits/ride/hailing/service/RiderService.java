package com.bits.ride.hailing.service;

import com.bits.ride.hailing.dto.RiderResponseDTO;
import com.bits.ride.hailing.entity.User;

import java.util.List;

/**
 * Service contract for rider-related operations.
 *
 * Implementations of this interface provide methods to fetch rider information
 * either individually or as lists of DTOs.
 */
public interface RiderService {

    /**
     * Retrieve all riders as DTOs.
     *
     * @return list of {@link RiderResponseDTO}; may be empty but never null
     */
    List<RiderResponseDTO> getAllRiders();

    /**
     * Retrieve a single user representation by id.
     *
     * @param id the id of the user to retrieve; may be null
     * @return a {@link User} when found, otherwise null
     */
    User getUser(Long id);

}
