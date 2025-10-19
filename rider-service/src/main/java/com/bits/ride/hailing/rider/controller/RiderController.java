package com.bits.ride.hailing.rider.controller;

import com.bits.ride.hailing.dto.RiderResponseDTO;
import com.bits.ride.hailing.entity.User;
import com.bits.ride.hailing.service.RiderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rider-service")
public class RiderController {

    private final RiderService riderService;

    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }

    @GetMapping("/v1/riders")
    public ResponseEntity<List<RiderResponseDTO>> getAllRiders() {
        return ResponseEntity.ok(riderService.getAllRiders());
    }

    @GetMapping("/v1/riders/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        User user = riderService.getUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}

