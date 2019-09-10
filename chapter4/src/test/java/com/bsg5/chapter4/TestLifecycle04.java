package com.bsg5.chapter4;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

class FourthObject extends HasData
        implements InitializingBean, DisposableBean {
    static Object semaphore = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        semaphore = new Object();
    }

    @Override
    public void destroy() throws Exception {
        semaphore = null;

    }
}

@ContextConfiguration(locations = "/config-04.xml")
public class TestLifecycle04 extends AbstractTestNGSpringContextTests {
    @Autowired
    ConfigurableApplicationContext context;

    @Test
    public void testLifecycleMethods() {
        FourthObject o1 = context.getBean(FourthObject.class);
        assertNotNull(FourthObject.semaphore);
        assertEquals(o1.getDatum(), "default");
        context.close();
        assertNull(FourthObject.semaphore);
    }
}
