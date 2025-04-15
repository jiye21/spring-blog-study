package com.estsoft.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AssertJTest {
    @Test
    public void test() {
        int a = 3;
        int b = 1;
        int result = 4;

        assertThat(a + b).isEqualTo(result);
    }
}
