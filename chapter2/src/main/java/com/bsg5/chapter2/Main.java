package com.bsg5.chapter2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {


    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Greeter greeter = context.getBean("helloGreeter", Greeter.class);
        greeter.greet();
    }
}
