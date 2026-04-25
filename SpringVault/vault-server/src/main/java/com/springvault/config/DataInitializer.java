package com.springvault.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Logging to terminal");
        log.info("Logging to terminal2");
    }
}
