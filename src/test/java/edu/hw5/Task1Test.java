package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {

    @Test
    @DisplayName("Тестирование среднего времени сеанса с стандартным вводом")
    public void testAverageSessionTimeWithStandardInput() {
        // Arrange
        String[] sessions = {
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        };
        Task1 task1 = new Task1();

        // Act
        long average = task1.calculateAverageSessionTime(sessions);

        // Assert
        long expectedAverage = 220; // 220 минут = 3 часа 40 минут
        assertEquals(expectedAverage, average, "Среднее время сеанса должно быть правильно рассчитано для стандартного ввода");
    }

    @Test
    @DisplayName("Тестирование среднего времени сеанса с пустым вводом")
    public void testAverageSessionTimeWithEmptyInput() {
        // Arrange
        String[] sessions = {};
        Task1 task1 = new Task1();

        // Act
        long average = task1.calculateAverageSessionTime(sessions);

        // Assert
        long expectedAverage = 0;
        assertEquals(expectedAverage, average, "Среднее время сеанса должно быть 0 для пустого ввода");
    }

    @Test
    @DisplayName("Тестирование среднего времени сеанса с короткими сессиями")
    public void testAverageSessionTimeWithShortSessions() {
        // Arrange
        String[] sessions = {
            "2022-03-12, 10:00 - 2022-03-12, 10:30",
            "2022-03-12, 11:00 - 2022-03-12, 11:15"
        };
        Task1 task1 = new Task1();

        // Act
        long average = task1.calculateAverageSessionTime(sessions);

        // Assert
        long expectedAverage = 22;
        assertEquals(expectedAverage, average, "Среднее время сеанса должно быть правильно рассчитано для коротких сессий");
    }

    @Test
    @DisplayName("Тестирование среднего времени сеанса при переходе через полночь")
    public void testAverageSessionTimeCrossingMidnight() {
        // Arrange
        String[] sessions = {
            "2022-03-12, 23:00 - 2022-03-13, 01:00",
            "2022-03-13, 23:30 - 2022-03-14, 00:15"
        };
        Task1 task1 = new Task1();

        // Act
        long average = task1.calculateAverageSessionTime(sessions);

        // Assert
        long expectedAverage = 82;
        assertEquals(expectedAverage, average, "Среднее время сеанса должно быть правильно рассчитано для сессий, переходящих через полночь");
    }

    @Test
    @DisplayName("Тестирование среднего времени сеанса с null вводом")
    public void testAverageSessionTimeWithNullInput() {
        // Arrange
        String[] sessions = null;
        Task1 task1 = new Task1();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> task1.calculateAverageSessionTime(sessions), "Sessions cannot be null");
    }

}
