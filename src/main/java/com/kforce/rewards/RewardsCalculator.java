package com.kforce.rewards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


/**
 * Rewards Calculator Main class
 */
@SpringBootApplication
@EnableCaching
public class RewardsCalculator {

    public static void main(String[] args) {
        SpringApplication.run(RewardsCalculator.class, args);
    }

}
