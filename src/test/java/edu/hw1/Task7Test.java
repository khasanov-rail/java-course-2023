package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task7Test {

    @Test
    public void testTaskStandardCases() {
        // Test the provided examples
        assertEquals(4, Task7.rotateRight(8, 1));
        assertEquals(1, Task7.rotateLeft(16, 1));
        assertEquals(6, Task7.rotateLeft(17, 2));
    }

    @Test
    public void testRotateRight() {
        assertEquals(4, Task7.rotateRight(8, 1));
        assertEquals(2, Task7.rotateRight(4, 1));
        assertEquals(1, Task7.rotateRight(2, 1));
        assertEquals(1, Task7.rotateRight(1, 1));
        assertEquals(17, Task7.rotateRight(17, 5));
    }

    @Test
    public void testRotateLeft() {
        assertEquals(1, Task7.rotateLeft(16, 1));
        assertEquals(1, Task7.rotateLeft(1, 1));
        assertEquals(1, Task7.rotateLeft(2, 1));
        assertEquals(1, Task7.rotateLeft(8, 1));
        assertEquals(17, Task7.rotateLeft(17, 5));
        assertEquals(6, Task7.rotateLeft(17, 2));
    }

    @Test
    public void testLargeShifts() {
        // Testing large shifts to ensure cyclical behavior
        assertEquals(4, Task7.rotateRight(8, 33));
        assertEquals(4, Task7.rotateLeft(16, 33));
    }

    @Test
    void leftMSB() {
        assertEquals(Task7.MSB, Task7.rotateLeft(Task7.MSB, Integer.SIZE)); // тест на крайние случаи
    }

    @Test
    void rightMSB() {
        assertEquals(Task7.MSB, Task7.rotateRight(Task7.MSB, Integer.SIZE));  // тест на крайние случаи
    }

}
