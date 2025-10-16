package com.bits.ride.hailing.controller;

import com.bits.ride.hailing.dto.RiderResponseDTO;
import com.bits.ride.hailing.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class RideHailingController {
    @Autowired
    private RiderService riderService;

    @GetMapping("/riders")
    public List<RiderResponseDTO> getAllRiders() {
        return riderService.getAllRiders();
    }

}

