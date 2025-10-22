package com.bits.ride.hailing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entry point for the Rider service Spring Boot application.
 *
 * <p>This class configures component scanning and default properties (port and context
 * path) and starts the Spring application.
 */
@SpringBootApplication(
    scanBasePackages = {
        "com.bits.ride.hailing.rider",
        "com.bits.ride.hailing.service",
        "com.bits.ride.hailing.entity",
        "com.bits.ride.hailing.dto",
        "com.bits.ride.hailing.repository"
    }
)
@EnableJpaRepositories(basePackages = "com.bits.ride.hailing.repository")
@EntityScan(basePackages = "com.bits.ride.hailing.entity")
public class RiderServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(RiderServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RiderServiceApplication.class);
        Map<String, Object> props = new HashMap<>();
        props.put("server.port", "8081");
        props.put("server.servlet.context-path", "/rider-service");
        app.setDefaultProperties(props);
        logger.info("Starting RiderService with properties: server.port={} context-path={}", props.get("server.port"), props.get("server.servlet.context-path"));
        app.run(args);
    }
}
