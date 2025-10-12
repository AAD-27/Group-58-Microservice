package com.bits.ride.hailing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class, org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class})
public class RiderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RiderServiceApplication.class, args);
    }
}
