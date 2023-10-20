package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task2Test {

    @ParameterizedTest
    @CsvSource({
        "4666, 4",
        "544, 3",
        "0, 1",
        "12345, 5",
        "-5, 1",
        "999999, 6",
        "1000000, 7",
        "-99, 2",
        "-100, 3",
        "" + Integer.MAX_VALUE + ", 10",
        "" + Integer.MIN_VALUE + ", 10"
    })
    public void testCountDigits(int number, int expectedDigitsCount) {
        // Act
        int result = Task2.countDigits(number);

        // Assert
        Assertions.assertEquals(expectedDigitsCount, result);
    }
}
