package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task7Test {

    @Test
    public void testTaskStandardCases() {
        // Arrange
        int input1 = 8;
        int shift1 = 1;
        int input2 = 16;
        int shift2 = 1;
        int input3 = 17;
        int shift3 = 2;

        // Act
        int result1 = Task7.rotateRight(input1, shift1);
        int result2 = Task7.rotateLeft(input2, shift2);
        int result3 = Task7.rotateLeft(input3, shift3);

        // Assert
        assertEquals(4, result1);
        assertEquals(1, result2);
        assertEquals(6, result3);
    }

    @Test
    public void testRotateRight() {
        // Arrange
        int[] inputs = {8, 4, 2, 1, 17};
        int[] shifts = {1, 1, 1, 1, 5};

        // Act
        int[] results = new int[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            results[i] = Task7.rotateRight(inputs[i], shifts[i]);
        }

        // Assert
        int[] expected = {4, 2, 1, 1, 17};
        for (int i = 0; i < results.length; i++) {
            assertEquals(expected[i], results[i]);
        }
    }

    @Test
    public void testRotateLeft() {
        // Arrange
        int[] inputs = {16, 1, 2, 8, 17, 17};
        int[] shifts = {1, 1, 1, 1, 5, 2};

        // Act
        int[] results = new int[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            results[i] = Task7.rotateLeft(inputs[i], shifts[i]);
        }

        // Assert
        int[] expected = {1, 1, 1, 1, 17, 6};
        for (int i = 0; i < results.length; i++) {
            assertEquals(expected[i], results[i]);
        }
    }

    @Test
    public void testLargeShifts() {
        // Arrange
        int input1 = 8;
        int shift1 = 33;
        int input2 = 16;
        int shift2 = 33;

        // Act
        int result1 = Task7.rotateRight(input1, shift1);
        int result2 = Task7.rotateLeft(input2, shift2);

        // Assert
        assertEquals(4, result1);
        assertEquals(4, result2);
    }

    @Test
    void leftMSB() {
        // Arrange
        int input = Task7.MSB;
        int shift = Integer.SIZE;

        // Act
        int result = Task7.rotateLeft(input, shift);

        // Assert
        assertEquals(Task7.MSB, result);
    }

    @Test
    void rightMSB() {
        // Arrange
        int input = Task7.MSB;
        int shift = Integer.SIZE;

        // Act
        int result = Task7.rotateRight(input, shift);

        // Assert
        assertEquals(Task7.MSB, result);
    }
}
