package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task4SingleTest {

    @Test
    @DisplayName("Проверка приближения числа Пи в однопоточном режиме")
    void testCalculatePiSingleThreaded() {
        // Arrange
        int numberOfSimulations = 1_000_000;

        // Act
        double pi = Task4Single.calculatePi(numberOfSimulations);

        // Assert
        double errorMargin = Math.abs(Math.PI - pi);
        assertTrue(errorMargin < 0.01, "Погрешность должна быть меньше 0.01");
    }
}
