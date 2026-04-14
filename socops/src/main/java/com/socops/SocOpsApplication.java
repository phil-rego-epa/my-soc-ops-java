package com.socops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Social Operations Bingo application.
 * A Spring Boot web application that serves a social bingo game for in-person mixers.
 */
@SpringBootApplication
public class SocOpsApplication {

    /**
     * Main entry point for the Spring Boot application.
     * @param commandLineArgs command line arguments passed to the application
     */
    public static void main(final String[] commandLineArgs) {
        SpringApplication.run(SocOpsApplication.class, commandLineArgs);
    }
}
