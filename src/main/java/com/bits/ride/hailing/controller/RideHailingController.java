package com.bits.ride.hailing.controller;

import com.bits.ride.hailing.dto.RiderResponseDTO;
import com.bits.ride.hailing.entity.User;
import com.bits.ride.hailing.service.RiderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class RideHailingController {
    private final RiderService riderService;

    // constructor injection is preferred and plays nicer with tests and IDE inspections
    public RideHailingController(RiderService riderService) {
        this.riderService = riderService;
    }

    @GetMapping("/riders")
    public ResponseEntity<List<RiderResponseDTO>> getAllRiders() {
        List<RiderResponseDTO> riders = riderService.getAllRiders();
        return ResponseEntity.ok(riders);
    }

    @GetMapping("/riders/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        User user = riderService.getUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

}
