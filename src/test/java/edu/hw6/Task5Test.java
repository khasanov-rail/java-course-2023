package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task5Test {

    @Test
    @DisplayName("Тестирование получения ID топ статей")
    void testHackerNewsTopStories() {
        // Act
        long[] topStories = Task5.hackerNewsTopStories();

        // Assert
        assertNotNull(topStories, "Массив ID не должен быть null");
        assertTrue(topStories.length > 0, "Массив ID должен содержать элементы");
    }

    @Test
    @DisplayName("Тестирование получения заголовка новости")
    void testNews() {
        // Arrange
        long[] topStories = Task5.hackerNewsTopStories();
        assertTrue(topStories.length > 0, "Должны быть доступны ID новостей для теста");

        // Act
        String title = Task5.news(topStories[0]);

        // Assert
        assertNotNull(title, "Заголовок новости не должен быть null");
        assertFalse(title.isEmpty(), "Заголовок новости не должен быть пустым");
    }
}
