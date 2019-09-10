package com.bsg5.chapter3;

import com.bsg5.chapter3.mem03.CapLeadingNormalizer;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestCapLeadingNormalizer {
    Normalizer normalizer=new CapLeadingNormalizer();

    @DataProvider
    Object[][] data() {
        return new Object[][] {
                { "this is a test", "This Is A Test"},
                { " This IS a test ", "This Is A Test"},
                { "this     is   a test", "This Is A Test"}
        };
    }

    @Test(dataProvider = "data")
    public void testNormalization(String input, String expected) {
        assertEquals(normalizer.transform(input), expected);
    }
}
