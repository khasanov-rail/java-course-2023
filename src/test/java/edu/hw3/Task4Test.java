package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task4Test {

    @Test
    public void testConvertToRomanForSmallNumbers() {
        // Arrange
        int input = 2;
        String expected = "II";

        // Act
        String result = Task4.convertToRoman(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testConvertToRomanForMediumNumbers() {
        // Arrange
        int input = 12;
        String expected = "XII";

        // Act
        String result = Task4.convertToRoman(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testConvertToRomanForMixedNumbers() {
        // Arrange
        int input = 16;
        String expected = "XVI";

        // Act
        String result = Task4.convertToRoman(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testConvertToRomanForLargeNumbers() {
        // Arrange
        int input = 1984;
        String expected = "MCMLXXXIV";

        // Act
        String result = Task4.convertToRoman(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testConvertToRomanForInvalidSmallNumber() {
        // Arrange
        int input = 0;

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> Task4.convertToRoman(input));
    }

    @Test
    public void testConvertToRomanForInvalidLargeNumber() {
        // Arrange
        int input = 4000;

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> Task4.convertToRoman(input));
    }
}
