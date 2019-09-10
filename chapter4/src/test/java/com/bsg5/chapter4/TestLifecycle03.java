package com.bsg5.chapter4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

class ThirdObject extends HasData {
    static Object semaphore = null;

    public void init() {
        semaphore = new Object();
    }

    public void dispose() {
        semaphore = null;
    }
}

@ContextConfiguration(locations = "/config-03.xml")
public class TestLifecycle03 extends AbstractTestNGSpringContextTests {
    @Autowired
    ConfigurableApplicationContext context;

    @Test
    public void testInitDestroyMethods() {
        ThirdObject o1 = context.getBean(ThirdObject.class);
        assertNotNull(ThirdObject.semaphore);
        assertEquals(o1.getDatum(), "default");
        context.close();
        assertNull(ThirdObject.semaphore);
    }
}
