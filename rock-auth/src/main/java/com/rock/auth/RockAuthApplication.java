package com.rock.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 启动类
 * @author liu kai
 * @since  2020-01-03 21:39
 */
@SpringBootApplication(scanBasePackages = "com.rock")
@Slf4j
public class RockAuthApplication {
    private static ConfigurableApplicationContext ctx;

    public static void main(String[] args) {
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        System.setProperty("log4.fileName","opay-scene-auth-center-server");
        ctx = SpringApplication.run(RockAuthApplication.class, args);
        log.info("spring.profiles.active:{}",ctx.getEnvironment().getActiveProfiles());
        log.info("Boot Server started.");
    }
}
