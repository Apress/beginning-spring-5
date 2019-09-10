package com.bsg5.chapter4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.testng.Assert.*;

class NinthObject extends HasData {
}

@Configuration
class Config08 {
    @Bean
    public NinthObject foo() {
        return new NinthObject();
    }

    @Bean
    @Scope("prototype")
    public NinthObject bar() {
        return new NinthObject();
    }
}

@ContextConfiguration(classes=Config08.class)
public class TestLifecycle08 extends AbstractTestNGSpringContextTests {
    @Autowired
    ApplicationContext context;

    @DataProvider
    Object[][] getReferences() {
        return new Object[][]{
                {"foo", true},
                {"bar", false}
        };
    }

    @Test(dataProvider = "getReferences")
    public void testReferenceTypes(String reference, boolean singleton) {
        HasData o1 = context.getBean(reference, HasData.class);

        String defaultValue = o1.getDatum();
        o1.setDatum(UUID.randomUUID().toString());

        HasData o2 = context.getBean(reference, HasData.class);
        if (singleton) {
            assertSame(o1, o2);
            assertEquals(o1, o2);
            assertNotEquals(defaultValue, o2.getDatum());
        } else {
            assertNotSame(o1, o2);
            assertNotEquals(o1, o2);
            assertEquals(defaultValue, o2.getDatum());
        }
    }
}
