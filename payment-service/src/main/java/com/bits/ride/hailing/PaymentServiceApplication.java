package com.bits.ride.hailing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(scanBasePackages = {"com.bits.ride.hailing.payment","com.bits.ride.hailing.service","com.bits.ride.hailing.entity","com.bits.ride.hailing.dto","com.bits.ride.hailing.repository"})
public class PaymentServiceApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PaymentServiceApplication.class);
        Map<String, Object> props = new HashMap<>();
        props.put("server.port", "8084");
        props.put("server.servlet.context-path", "/payment-service");
        app.setDefaultProperties(props);
        app.run(args);
    }
}
