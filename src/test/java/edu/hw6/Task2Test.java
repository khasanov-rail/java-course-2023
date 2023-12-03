package edu.hw6;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task2Test {
    private Path testFile;

    @BeforeEach
    void setUp() throws Exception {
        // Создаем временный файл для тестирования
        testFile = Files.createTempFile("testFile", ".txt");
    }

    @Test
    @DisplayName("Копирование файла с уникальным именем")
    void testCloneFileUniqueName() throws Exception {
        // Act
        Path clonedFile = Task2.cloneFile(testFile);

        // Assert
        assertTrue(Files.exists(clonedFile), "Клонированный файл должен существовать");
        assertNotEquals(testFile, clonedFile, "Пути клонированного и оригинального файла должны отличаться");
        assertTrue(
            clonedFile.getFileName().toString().startsWith("testFile"),
            "Имя клонированного файла должно начинаться с 'testFile'"
        );
    }

    @AfterEach
    void tearDown() throws Exception {
        // Удаляем тестовый и клонированный файлы после завершения теста
        Files.deleteIfExists(testFile);
        Files.deleteIfExists(Paths.get(testFile.toString().replace(".txt", " — копия.txt")));
    }
}
