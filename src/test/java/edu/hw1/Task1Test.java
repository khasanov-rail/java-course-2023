package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    @Test
    public void testCorrectValues() {
        assertEquals(60, Task1.minutesToSeconds("01:00"));
        assertEquals(836, Task1.minutesToSeconds("13:56"));
        assertEquals(5999, Task1.minutesToSeconds("99:59"));
    }

    @Test
    public void testLargeMinutesValues() {
        assertEquals(60059, Task1.minutesToSeconds("1000:59"));
        assertEquals(59999, Task1.minutesToSeconds("999:59"));
        assertEquals(120000, Task1.minutesToSeconds("2000:00"));
    }


    @Test
    public void testIncorrectSecondsValues() {
        assertEquals(-1, Task1.minutesToSeconds("10:60"));
        assertEquals(-1, Task1.minutesToSeconds("10:61"));
    }

    @Test
    public void testIncorrectFormat() {
        assertEquals(-1, Task1.minutesToSeconds("10:601"));
        assertEquals(-1, Task1.minutesToSeconds("10"));
        assertEquals(-1, Task1.minutesToSeconds(":10"));
        assertEquals(-1, Task1.minutesToSeconds("10:"));
        assertEquals(-1, Task1.minutesToSeconds(":"));
        assertEquals(-1, Task1.minutesToSeconds("10:10:10"));
    }

    @Test
    public void testBoundaryValues() {
        assertEquals(0, Task1.minutesToSeconds("00:00"));
        assertEquals(1, Task1.minutesToSeconds("00:01"));
        assertEquals(59, Task1.minutesToSeconds("00:59"));
    }

    @Test
    public void testNegativeValues() {
        assertEquals(-1, Task1.minutesToSeconds("-10:10")); // тест на отрицательные минуты
        assertEquals(-1, Task1.minutesToSeconds("10:-10")); // тест на отрицательные секунды
    }
}
