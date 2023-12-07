package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task4MultiTest {

    @Test
    @DisplayName("Проверка приближения числа Пи с положительным числом симуляций")
    void testCalculatePiWithPositiveSimulations() {
        // Arrange
        int numberOfSimulations = 1_000_000;

        // Act
        double pi = Task4Multi.calculatePi(numberOfSimulations);

        // Assert
        double errorMargin = Math.abs(Math.PI - pi);
        assertTrue(errorMargin < 0.01, "Погрешность должна быть меньше 0.01");
    }

    @Test
    @DisplayName("Проверка вызова исключения при нулевом количестве симуляций")
    void testCalculatePiWithZeroSimulations() {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Task4Multi.calculatePi(0),
            "Должно быть выброшено исключение IllegalArgumentException при нулевом количестве симуляций"
        );
    }

    @Test
    @DisplayName("Проверка вызова исключения при отрицательном количестве симуляций")
    void testCalculatePiWithNegativeSimulations() {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Task4Multi.calculatePi(-100),
            "Должно быть выброшено исключение IllegalArgumentException при отрицательном количестве симуляций"
        );
    }
}
