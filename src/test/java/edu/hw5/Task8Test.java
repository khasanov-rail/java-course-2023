package edu.hw5;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task8Test {

    @ParameterizedTest
    @CsvSource({
        "101, true",
        "10, false",
        "1, true",
        "11111, true",
        "00, false"
    })
    @DisplayName("Тестирование нечетной длины")
    void testIsOddLength(String input, boolean expected) {
        // Arrange & Act
        boolean result = Task8.isOddLength(input);

        // Assert
        assertEquals(expected, result, "Строка должна быть нечетной длины");
    }

    @ParameterizedTest
    @CsvSource({
        "1, false",
        "110, false"
    })
    @DisplayName(
        "Тестирование строки, начинающейся с 0 и имеющей нечетную длину, или начинающейся с 1 и имеющей четную длину")
    void testStartsWith0OddLengthOr1EvenLength(String input, boolean expected) {
        // Arrange & Act
        boolean result = Task8.startsWith0OddLengthOr1EvenLength(input);

        // Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "000, true",
        "111000, true",
    })
    @DisplayName("Тестирование строки, в которой количество 0 кратно 3")
    void testZeroCountMultipleOfThree(String input, boolean expected) {
        // Arrange & Act
        boolean result = Task8.zeroCountMultipleOfThree(input);

        // Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "10, true",
        "101, true",
        "11, false",
        "111, false"
    })
    @DisplayName("Тестирование любой строки, кроме 11 или 111")
    void testNot11Or111(String input, boolean expected) {
        // Arrange & Act
        boolean result = Task8.not11Or111(input);

        // Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "101, true",
        "111, true",
        "110, false"
    })
    @DisplayName("Тестирование строки, где каждый нечетный символ равен 1")
    void testEveryOddCharIs1(String input, boolean expected) {
        // Arrange & Act
        boolean result = Task8.everyOddCharIs1(input);

        // Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "001, true",
        "010, true",
        "011, false",
        "1, false"
    })
    @DisplayName("Тестирование строки, содержащей не менее двух 0 и не более одной 1")
    void testAtLeastTwoZerosAtMostOneOne(String input, boolean expected) {
        // Arrange & Act
        boolean result = Task8.atLeastTwoZerosAtMostOneOne(input);

        // Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "01, true",
        "1001, true",
        "11, false",
        "11101, false"
    })
    @DisplayName("Тестирование строки, не содержащей последовательных 1")
    void testNoConsecutiveOnes(String input, boolean expected) {
        // Arrange & Act
        boolean result = Task8.noConsecutiveOnes(input);

        // Assert
        assertEquals(expected, result);
    }
}
