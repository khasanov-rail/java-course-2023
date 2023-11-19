package edu.hw5;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class Task5Test {

    @Test
    @DisplayName("Проверка валидности корректного российского номерного знака")
    public void testValidRussianLicensePlate() {
        // Arrange
        String validPlate = "А123ВЕ777";

        // Act
        boolean result = Task5.isValidRussianLicensePlate(validPlate);

        // Assert
        assertTrue(result, "Номер должен быть действительным");
    }

    @Test
    @DisplayName("Проверка невалидности некорректного российского номерного знака")
    public void testInvalidRussianLicensePlate() {
        // Arrange
        String invalidPlate1 = "123АВЕ777";
        String invalidPlate2 = "А123ВГ77";
        String invalidPlate3 = "А123ВЕ7777";

        // Act and Assert
        assertFalse(Task5.isValidRussianLicensePlate(invalidPlate1), "Номер с цифрами в начале должен быть недействительным");
        assertFalse(Task5.isValidRussianLicensePlate(invalidPlate2), "Номер с неправильными буквами должен быть недействительным");
        assertFalse(Task5.isValidRussianLicensePlate(invalidPlate3), "Номер с избыточным числом цифр должен быть недействительным");
    }

    @Test
    @DisplayName("Проверка валидности номерного знака с null вводом")
    public void testValidRussianLicensePlateWithNullInput() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Task5.isValidRussianLicensePlate(null), "License plate can not be null");
    }
}
