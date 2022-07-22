package com.rock.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.rock")
@Slf4j
public class RockAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(RockAuthApplication.class, args);
        log.info("Boot Server started.");
    }
}
