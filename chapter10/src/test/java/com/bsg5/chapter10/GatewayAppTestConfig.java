package com.bsg5.chapter10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebAppConfiguration
public class GatewayAppTestConfig {
    @Autowired
    ApplicationContext context;

    @Bean
    WebTestClient webTestClient() {
        return WebTestClient.bindToApplicationContext(context)
                .build();
    }
}
