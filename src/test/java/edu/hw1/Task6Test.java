package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task6Test {

    @Test
    void testCountKForKnownNumbers() {
        assertEquals(3, Task6.countK(3524));
        assertEquals(5, Task6.countK(6621));
        assertEquals(4, Task6.countK(6554));
        assertEquals(3, Task6.countK(1234));
    }

    @Test
    void testCountKForNegativeNumbers() {
        assertEquals(-1, Task6.countK(-1234));
        assertEquals(-1, Task6.countK(-3524));
    }

    @Test
    void testCountKForNumbersLessThan1000() {
        assertEquals(-1, Task6.countK(999));
        assertEquals(-1, Task6.countK(123));
        assertEquals(-1, Task6.countK(100));
    }

    @Test
    void testCountKForNumber1000() {
        assertEquals(-1, Task6.countK(1000));
    }

    @Test
    void testCountKForNumbersGreaterThan9999() {
        assertEquals(-1, Task6.countK(10000));
        assertEquals(-1, Task6.countK(12345));
    }

    @Test
    void testCountKForNumbersWithSameDigits() {
        assertEquals(-1, Task6.countK(1111));
        assertEquals(-1, Task6.countK(2222));
        assertEquals(-1, Task6.countK(3333));
        assertEquals(-1, Task6.countK(4444));
        assertEquals(-1, Task6.countK(5555));
        assertEquals(-1, Task6.countK(6666));
        assertEquals(-1, Task6.countK(7777));
        assertEquals(-1, Task6.countK(8888));
        assertEquals(-1, Task6.countK(9999));
    }

    @Test
    void testCountKForKaprekarNumber() {
        assertEquals(0, Task6.countK(6174));
    }

    @Test
    void testCountKForRandomNumbers() {
        assertEquals(4, Task6.countK(9995));
        assertEquals(5, Task6.countK(9832));
        assertEquals(4, Task6.countK(1001));
    }
}
