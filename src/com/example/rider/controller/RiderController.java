package com.bits.ride.hailing.controller;

import com.bits.ride.hailing.bo.RiderBO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/riders")
public class RiderController {
    @GetMapping("/{id}")
    public RiderBO getRider(@PathVariable Long id) {
        // Dummy data for demonstration
        return new RiderBO(id, "John Doe", "john.doe@example.com");
    }
}
