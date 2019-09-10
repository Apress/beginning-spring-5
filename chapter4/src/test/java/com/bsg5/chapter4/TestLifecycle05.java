package com.bsg5.chapter4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.testng.Assert.*;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
class FifthObject extends HasData {
}

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class SixthObject extends HasData {
}

@ContextConfiguration("/annotated.xml")
public class TestLifecycle05 extends AbstractTestNGSpringContextTests {
    @Autowired
    ApplicationContext context;

    @DataProvider
    Object[][] getReferences() {
        return new Object[][]{
                {FifthObject.class, true},
                {SixthObject.class, false}
        };
    }

    @Test(dataProvider = "getReferences")
    public void testReferenceTypes(Class<HasData> clazz, boolean singleton) {
        HasData o1 = context.getBean(clazz);

        String defaultValue = o1.getDatum();
        o1.setDatum(UUID.randomUUID().toString());

        HasData o2 = context.getBean(clazz);
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
