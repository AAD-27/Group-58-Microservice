package com.bits.ride.hailing.service;

import com.bits.ride.hailing.dto.RiderResponseDTO;
import com.bits.ride.hailing.entity.User;

import java.util.List;

public interface RiderService {
    List<RiderResponseDTO> getAllRiders();

    User getUser(Long id);

}
