package edu.hw5;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class Task4Test {

    @Test
    @DisplayName("Пароль содержит специальный символ")
    public void testValidPasswordWithSpecialCharacter() {
        // Arrange
        String validPassword = "Password123!";

        // Act
        boolean result = Task4.isValidPassword(validPassword);

        // Assert
        assertTrue(result, "Пароль должен быть действительным, так как содержит специальный символ");
    }

    @Test
    @DisplayName("Пароль не содержит специальных символов")
    public void testInvalidPasswordWithoutSpecialCharacter() {
        // Arrange
        String invalidPassword = "Password123";

        // Act
        boolean result = Task4.isValidPassword(invalidPassword);

        // Assert
        assertFalse(result, "Пароль должен быть недействительным, так как не содержит специальных символов");
    }

    @Test
    @DisplayName("Проверка валидности пароля с null вводом")
    public void testValidPasswordWithNullInput() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Task4.isValidPassword(null), "Password can not be null");
    }
}
