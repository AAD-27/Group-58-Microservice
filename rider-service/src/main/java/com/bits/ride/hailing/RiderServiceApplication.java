package com.bits.ride.hailing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class, org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class})
public class RiderServiceApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RiderServiceApplication.class);
        Map<String, Object> props = new HashMap<>();
        props.put("server.port", "8081");
        props.put("server.servlet.context-path", "/rider-service");
        app.setDefaultProperties(props);
        app.run(args);
    }
}
