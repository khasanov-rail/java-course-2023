package edu.hw5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Task2Test {

    @Test
    @DisplayName("Поиск всех пятниц 13-го в заданном году")
    public void testFindFridays13() {
        // Arrange
        int year1925 = 1925;
        int year2024 = 2024;
        List<LocalDate> expected1925 = Arrays.asList(LocalDate.of(1925, 2, 13), LocalDate.of(1925, 3, 13), LocalDate.of(1925, 11, 13));
        List<LocalDate> expected2024 = Arrays.asList(LocalDate.of(2024, 9, 13), LocalDate.of(2024, 12, 13));

        // Act
        List<LocalDate> result1925 = Task2.findFridays13(year1925);
        List<LocalDate> result2024 = Task2.findFridays13(year2024);

        // Assert
        assertEquals(expected1925, result1925, "Пятницы 13-го в 1925 году должны быть найдены правильно");
        assertEquals(expected2024, result2024, "Пятницы 13-го в 2024 году должны быть найдены правильно");
    }

    @Test
    @DisplayName("Поиск следующей пятницы 13-го после заданной даты")
    public void testFindNextFriday13() {
        // Arrange
        LocalDate fromDate = LocalDate.of(2022, 8, 14);
        LocalDate expectedNextFriday13 = LocalDate.of(2023, 1, 13);

        // Act
        LocalDate result = Task2.findNextFriday13(fromDate);

        // Assert
        assertEquals(expectedNextFriday13, result, "Следующая пятница 13-го после 2022-08-14 должна быть найдена правильно");
    }
}
