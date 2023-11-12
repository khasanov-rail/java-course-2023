package edu.hw5;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task7Test {

    @ParameterizedTest
    @CsvSource({
        "110, true",
        "010, true",
        "001, false"
    })
    @DisplayName("Тестирование шаблона 1")
    void testMatchesPattern1(String input, boolean expected) {
        // Arrange - подготовка данных уже выполнена с помощью CsvSource

        // Act
        boolean result = Task7.matchesPattern1(input);

        // Assert
        assertEquals(expected, result, "Тестирование шаблона 1");
    }

    @ParameterizedTest
    @CsvSource({
        "1, true",
        "000, true",
        "101, false"
    })
    @DisplayName("Тестирование шаблона 2")
    void testMatchesPattern2(String input, boolean expected) {
        // Arrange - подготовка данных уже выполнена с помощью CsvSource

        // Act
        boolean result = Task7.matchesPattern2(input);

        // Assert
        assertEquals(expected, result, "Тестирование шаблона 2");
    }

    @ParameterizedTest
    @CsvSource({
        "0, true",
        "11, true",
        "000, true",
        "0000, false"
    })
    @DisplayName("Тестирование шаблона 3")
    void testMatchesPattern3(String input, boolean expected) {
        // Arrange - подготовка данных уже выполнена с помощью CsvSource

        // Act
        boolean result = Task7.matchesPattern3(input);

        // Assert
        assertEquals(expected, result, "Тестирование шаблона 3");
    }

    @Test
    @DisplayName("Тестирование на null и пустую строку")
    void testWithNullAndEmptyString() {
        // Arrange - нет подготовки данных, не нужно

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Task7.matchesPattern1(null));
        assertThrows(IllegalArgumentException.class, () -> Task7.matchesPattern1(""));
        assertThrows(IllegalArgumentException.class, () -> Task7.matchesPattern2(null));
        assertThrows(IllegalArgumentException.class, () -> Task7.matchesPattern2(""));
        assertThrows(IllegalArgumentException.class, () -> Task7.matchesPattern3(null));
        assertThrows(IllegalArgumentException.class, () -> Task7.matchesPattern3(""));
    }
}
