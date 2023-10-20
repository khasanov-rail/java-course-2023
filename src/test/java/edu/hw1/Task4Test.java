package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Task4Test {

    @ParameterizedTest
    @MethodSource("commonCases")
    void testFixStringForCommonCases(String input, String expected) {
        // Arrange
        // В этом случае данные уже подготовлены через @MethodSource

        // Act
        String result = Task4.fixString(input);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> commonCases() {
        return Stream.of(
            Arguments.of("123456", "214365"),
            Arguments.of("hTsii  s aimex dpus rtni.g", "This is a mixed up string."),
            Arguments.of("badce", "abcde")
        );
    }

    @ParameterizedTest
    @MethodSource("edgeCases")
    void testFixStringForEdgeCases(String input, String expected) {
        // Arrange
        // В этом случае данные уже подготовлены через @MethodSource

        // Act
        String result = Task4.fixString(input);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> edgeCases() {
        return Stream.of(
            Arguments.of("", ""),
            Arguments.of(" ", " "),
            Arguments.of(null, null),
            Arguments.of("a", "a"),
            Arguments.of("ab", "ba")
        );
    }

    @ParameterizedTest
    @MethodSource("specialCharactersCases")
    void testFixStringForSpecialCharacters(String input, String expected) {
        // Arrange
        // В этом случае данные уже подготовлены через @MethodSource

        // Act
        String result = Task4.fixString(input);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> specialCharactersCases() {
        return Stream.of(
            Arguments.of("!@#$%^&*()_-=+?<>", "@!$#^%*&)(-_+=<?>")
        );
    }

    @Test
    void testFixStringForLongStrings() {
        // Arrange
        String longStr = "a" + "b".repeat(9999);
        String expectedStr = "ba" + "b".repeat(9998);

        // Act
        String result = Task4.fixString(longStr);

        // Assert
        Assertions.assertEquals(expectedStr, result);
    }
}
