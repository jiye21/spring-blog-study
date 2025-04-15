package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JUnitTest {
    @Test
    public void addTest() {
        int a = 1;
        int b = 2;
        int result = 3;

        Assertions.assertEquals(result, a + b);
    }
}
