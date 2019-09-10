package com.bsg5.chapter9.live;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.bsg5.chapter9.jpa",
        "com.bsg5.chapter9.live"
})
public class LiveJpaApp {
    public static void main(String[] args) {
        SpringApplication.run(LiveJpaApp.class, args).close();
    }
}
