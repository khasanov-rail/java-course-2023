package edu.hw5;

import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3Test {

    private final Task3 task3 = new Task3();

    @Test
    @DisplayName("Тестирование формата даты '2020-10-10'")
    public void testParseStandardDateFormat() {
        // Arrange
        String standardDate = "2020-10-10";

        // Act
        Optional<LocalDate> result = task3.parseDate(standardDate);

        // Assert
        assertEquals(
            LocalDate.of(2020, 10, 10),
            result.orElse(null),
            "Стандартная дата должна быть правильно разобрана"
        );
    }

    @Test
    @DisplayName("Тестирование формата даты '2020-12-2'")
    public void testParseStandardDateFormatWithSingleDigitDay() {
        // Arrange
        String date = "2020-12-2";

        // Act
        Optional<LocalDate> result = task3.parseDate(date);

        // Assert
        assertEquals(
            LocalDate.of(2020, 12, 2),
            result.orElse(null),
            "Дата с однозначным днем должна быть правильно разобрана"
        );
    }

    @Test
    @DisplayName("Тестирование американского формата даты '1/3/1976'")
    public void testParseAmericanDateFormat() {
        // Arrange
        String americanDate = "1/3/1976";

        // Act
        Optional<LocalDate> result = task3.parseDate(americanDate);

        // Assert
        assertEquals(
            LocalDate.of(1976, 1, 3),
            result.orElse(null),
            "Американская дата должна быть правильно разобрана"
        );
    }

    @Test
    @DisplayName("Тестирование американского формата даты '1/3/20'")
    public void testParseAmericanDateFormatWithTwoDigitYear() {
        // Arrange
        String date = "1/3/20";

        // Act
        Optional<LocalDate> result = task3.parseDate(date);

        // Assert
        assertEquals(
            LocalDate.of(2020, 1, 3),
            result.orElse(null),
            "Американская дата с двузначным годом должна быть правильно разобрана"
        );
    }

    @Test
    @DisplayName("Тестирование относительной даты 'tomorrow'")
    public void testParseTomorrow() {
        // Arrange
        String relativeDate = "tomorrow";

        // Act
        Optional<LocalDate> result = task3.parseDate(relativeDate);

        // Assert
        assertEquals(LocalDate.now().plusDays(1), result.orElse(null), "Дата 'завтра' должна быть правильно разобрана");
    }

    @Test
    @DisplayName("Тестирование относительной даты 'today'")
    public void testParseToday() {
        // Arrange
        String relativeDate = "today";

        // Act
        Optional<LocalDate> result = task3.parseDate(relativeDate);

        // Assert
        assertEquals(LocalDate.now(), result.orElse(null), "Дата 'сегодня' должна быть правильно разобрана");
    }

    @Test
    @DisplayName("Тестирование относительной даты 'yesterday'")
    public void testParseYesterday() {
        // Arrange
        String relativeDate = "yesterday";

        // Act
        Optional<LocalDate> result = task3.parseDate(relativeDate);

        // Assert
        assertEquals(LocalDate.now().minusDays(1), result.orElse(null), "Дата 'вчера' должна быть правильно разобрана");
    }

    @Test
    @DisplayName("Тестирование формата '1 day ago'")
    public void testParseOneDayAgo() {
        // Arrange
        String daysAgo = "1 day ago";

        // Act
        Optional<LocalDate> result = task3.parseDate(daysAgo);

        // Assert
        assertEquals(
            LocalDate.now().minusDays(1),
            result.orElse(null),
            "Дата '1 день назад' должна быть правильно разобрана"
        );
    }

    @Test
    @DisplayName("Тестирование формата '2234 days ago'")
    public void testParseManyDaysAgo() {
        // Arrange
        String daysAgo = "2234 days ago";

        // Act
        Optional<LocalDate> result = task3.parseDate(daysAgo);

        // Assert
        assertEquals(
            LocalDate.now().minusDays(2234),
            result.orElse(null),
            "Дата '2234 дня назад' должна быть правильно разобрана"
        );
    }

    @Test
    @DisplayName("Тестирование разбора даты с null вводом")
    public void testParseDateWithNullInput() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Task3().parseDate(null), "String cannot be null");
    }
}
