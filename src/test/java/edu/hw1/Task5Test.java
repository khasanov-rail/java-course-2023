package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Task5Test {

    @ParameterizedTest
    @MethodSource("trueCases")
    void testIsPalindromeDescendantTrueCases(int input) {
        // Arrange
        // В этом случае данные уже подготовлены через @MethodSource

        // Act
        boolean result = Task5.isPalindromeDescendant(input);

        // Assert
        Assertions.assertTrue(result);
    }

    private static Stream<Arguments> trueCases() {
        return Stream.of(
            Arguments.of(11211230),
            Arguments.of(13001120),
            Arguments.of(23336014),
            Arguments.of(11),
            Arguments.of(93287),
            Arguments.of(123),
            Arguments.of(1377)
        );
    }

    @ParameterizedTest
    @MethodSource("falseCases")
    void testIsPalindromeDescendantFalseCases(int input) {
        // Arrange
        // В этом случае данные уже подготовлены через @MethodSource

        // Act
        boolean result = Task5.isPalindromeDescendant(input);

        // Assert
        Assertions.assertFalse(result);
    }

    private static Stream<Arguments> falseCases() {
        return Stream.of(
            Arguments.of(12345678),
            Arguments.of(9),
            Arguments.of(45),
            Arguments.of(1233211),
            Arguments.of(-11),
            Arguments.of(-123),
            Arguments.of(-17),
            Arguments.of(17)
        );
    }
}
