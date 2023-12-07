package edu.hw7;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task2Test {

    @Test
    @DisplayName("Факториал 0 должен быть 1")
    void testFactorialOfZero() {
        // Arrange
        int number = 0;

        // Act
        BigInteger result = Task2.factorial(number);

        // Assert
        assertEquals(BigInteger.ONE, result, "Факториал 0 должен быть равен 1");
    }

    @Test
    @DisplayName("Факториал 1 должен быть 1")
    void testFactorialOfOne() {
        // Arrange
        int number = 1;

        // Act
        BigInteger result = Task2.factorial(number);

        // Assert
        assertEquals(BigInteger.ONE, result, "Факториал 1 должен быть равен 1");
    }

    @Test
    @DisplayName("Факториал 5 должен быть 120")
    void testFactorialOfFive() {
        // Arrange
        int number = 5;

        // Act
        BigInteger result = Task2.factorial(number);

        // Assert
        assertEquals(BigInteger.valueOf(120), result, "Факториал 5 должен быть равен 120");
    }

    @Test
    @DisplayName("Факториал большого числа (например, 20)")
    void testFactorialOfLargeNumber() {
        // Arrange
        int number = 20;
        BigInteger expectedFactorial = new BigInteger("2432902008176640000");

        // Act
        BigInteger result = Task2.factorial(number);

        // Assert
        assertEquals(expectedFactorial, result, "Факториал 20 должен быть равен 2432902008176640000");
    }

    @Test
    @DisplayName("Факториал числа больше 20 должен вызывать исключение")
    void testFactorialOfNumberGreaterThanTwenty() {
        // Arrange
        int number = 21;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Task2.factorial(number),
            "Должно быть выброшено исключение IllegalArgumentException для чисел больше 20"
        );
    }

    @Test
    @DisplayName("Факториал отрицательного числа должен вызывать исключение")
    void testFactorialOfNegativeNumber() {
        // Arrange
        int number = -1;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Task2.factorial(number),
            "Должно быть выброшено исключение IllegalArgumentException для отрицательного числа"
        );
    }
}
