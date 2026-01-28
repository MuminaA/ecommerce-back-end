package com.ecommercebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EcommerceBackEndApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcommerceBackEndApplication.class, args);
    }
}
