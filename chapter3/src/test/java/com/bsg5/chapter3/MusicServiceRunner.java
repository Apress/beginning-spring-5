package com.bsg5.chapter3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MusicServiceRunner {
    public static void main(String[] args) {
        Class<?>[] configurations = new Class<?>[]
                {Configuration7.class, TestConfiguration.class
                };
        ApplicationContext context =
                new AnnotationConfigApplicationContext(configurations);
        for(String name:context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }
}
