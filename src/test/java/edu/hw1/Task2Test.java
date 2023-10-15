package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    @Test
    public void testCountDigits() {
        assertEquals(4, Task2.countDigits(4666));
        assertEquals(3, Task2.countDigits(544));
        assertEquals(1, Task2.countDigits(0));
        assertEquals(5, Task2.countDigits(12345));
        assertEquals(1, Task2.countDigits(-5));
        assertEquals(6, Task2.countDigits(999999));
        assertEquals(7, Task2.countDigits(1000000));
        assertEquals(2, Task2.countDigits(-99));
        assertEquals(3, Task2.countDigits(-100));
        assertEquals(10, Task2.countDigits(Integer.MAX_VALUE));
        assertEquals(10, Task2.countDigits(Integer.MIN_VALUE));
    }
}
