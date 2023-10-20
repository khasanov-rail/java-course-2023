package edu.hw1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    @ParameterizedTest
    @CsvSource({
        "01:00, 60",
        "13:56, 836",
        "99:59, 5999"
    })
    public void testCorrectValues(String input, int expected) {
        // Act
        int result = Task1.minutesToSeconds(input);

        // Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "1000:59, 60059",
        "999:59, 59999",
        "2000:00, 120000"
    })
    public void testLargeMinutesValues(String input, int expected) {
        // Act
        int result = Task1.minutesToSeconds(input);

        // Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"10:60", "10:61"})
    public void testIncorrectSecondsValues(String input) {
        assertEquals(-1, Task1.minutesToSeconds(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"10:601", "10", ":10", "10:", ":", "10:10:10"})
    public void testIncorrectFormat(String input) {
        assertEquals(-1, Task1.minutesToSeconds(input));
    }

    @ParameterizedTest
    @CsvSource({
        "00:00, 0",
        "00:01, 1",
        "00:59, 59"
    })
    public void testBoundaryValues(String input, int expected) {
        // Act
        int result = Task1.minutesToSeconds(input);

        // Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-10:10", "10:-10"})
    public void testNegativeValues(String input) {
        assertEquals(-1, Task1.minutesToSeconds(input));
    }
}
