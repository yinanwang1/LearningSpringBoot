package com.yn.learningspringboot;

import com.yn.learningspringboot.util.Factorial;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactorialTest {

    @Test
    void testFact() {
        assertEquals(1, Factorial.factorial(0));
        assertEquals(1, Factorial.factorial(1));
        assertEquals(6, Factorial.factorial(3));
        assertEquals(362880, Factorial.factorial(10));
        assertEquals(2432902008176640000L, Factorial.factorial(20));
    }

    @Test
    void testNegative() {
        assertThrows(IllegalArgumentException.class, () -> Factorial.factorial(1));
    }
}
