package com.bsg5.chapter8;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
@EnableWebMvc
public class JdbcConfiguration {
}
