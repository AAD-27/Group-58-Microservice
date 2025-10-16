package com.bits.ride.hailing.service;

import com.bits.ride.hailing.bo.RiderBO;
import com.bits.ride.hailing.dto.RiderResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiderService {
    public List<RiderResponseDTO> getAllRiders() {

        return RiderBO.getAllRiders();
    }
}
