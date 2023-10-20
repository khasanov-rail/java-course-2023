package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Task3Test {

    @ParameterizedTest
    @MethodSource("nestableData")
    void testIsNestable(int[] array1, int[] array2, boolean expected) {
        // Arrange
        // В этом случае данные уже подготовлены через @MethodSource,

        // Act
        boolean result = Task3.isNestable(array1, array2);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> nestableData() {
        return Stream.of(
            Arguments.of(new int[] {1, 2, 3, 4}, new int[] {0, 6}, true),
            Arguments.of(new int[] {3, 1}, new int[] {4, 0}, true),
            Arguments.of(new int[] {9, 9, 8}, new int[] {8, 9}, false),
            Arguments.of(new int[] {1, 2, 3, 4}, new int[] {2, 3}, false),
            Arguments.of(new int[] {Integer.MIN_VALUE}, new int[] {Integer.MIN_VALUE}, false)
        );
    }

    @Test
    void testIsNestableForEmptyArrays() {
        // Arrange
        int[] emptyArray = {};
        int[] nonEmptyArray = {1, 2, 3};

        // Act
        boolean result1 = Task3.isNestable(emptyArray, nonEmptyArray);
        boolean result2 = Task3.isNestable(nonEmptyArray, emptyArray);
        boolean result3 = Task3.isNestable(emptyArray, emptyArray);

        // Assert
        Assertions.assertFalse(result1);
        Assertions.assertFalse(result2);
        Assertions.assertFalse(result3);
    }

    @Test
    void testIsNestableForNullArrays() {
        // Arrange
        int[] validArray = {1, 2, 3};
        int[] nullArray = null;

        // Act
        boolean result1 = Task3.isNestable(nullArray, validArray);
        boolean result2 = Task3.isNestable(validArray, nullArray);
        boolean result3 = Task3.isNestable(nullArray, nullArray);

        // Assert
        Assertions.assertFalse(result1);
        Assertions.assertFalse(result2);
        Assertions.assertFalse(result3);
    }
}
