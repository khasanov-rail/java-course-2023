package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Task6Test {

    @ParameterizedTest
    @MethodSource("knownNumbers")
    void testCountKForKnownNumbers(int input, int expected) {
        // Arrange
        int number = input;

        // Act
        int result = Task6.countK(number);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> knownNumbers() {
        return Stream.of(
            Arguments.of(3524, 3),
            Arguments.of(6621, 5),
            Arguments.of(6554, 4),
            Arguments.of(1234, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("boundaryAndSpecialCases")
    void testCountKForBoundaryAndSpecialCases(int input, int expected) {
        // Arrange
        int number = input;

        // Act
        int result = Task6.countK(number);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> boundaryAndSpecialCases() {
        return Stream.of(
            Arguments.of(-1234, -1),
            Arguments.of(-3524, -1),
            Arguments.of(999, -1),
            Arguments.of(123, -1),
            Arguments.of(100, -1),
            Arguments.of(1000, -1),
            Arguments.of(10000, -1),
            Arguments.of(12345, -1),
            Arguments.of(1111, -1),
            Arguments.of(2222, -1),
            Arguments.of(3333, -1),
            Arguments.of(4444, -1),
            Arguments.of(5555, -1),
            Arguments.of(6666, -1),
            Arguments.of(7777, -1),
            Arguments.of(8888, -1),
            Arguments.of(9999, -1),
            Arguments.of(6174, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("randomNumbers")
    void testCountKForRandomNumbers(int input, int expected) {
        // Arrange
        int number = input;

        // Act
        int result = Task6.countK(number);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> randomNumbers() {
        return Stream.of(
            Arguments.of(9995, 4),
            Arguments.of(9832, 5),
            Arguments.of(1001, 4)
        );
    }
}
