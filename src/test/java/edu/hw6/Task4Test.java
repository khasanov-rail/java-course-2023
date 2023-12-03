package edu.hw6;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task4Test {

    private Path testFile;

    @BeforeEach
    void setUp() throws Exception {
        testFile = Files.createTempFile("testFile", ".txt");
    }

    @Test
    @DisplayName("Тест записи в файл")
    void testWriteToFile() throws Exception {
        String text = "Какой то текст";

        // Act
        Task4.writeToFile(text, testFile);

        // Assert
        String fileContent = Files.readString(testFile);
        assertEquals(text + System.lineSeparator(), fileContent, "Содержимое файла должно соответствовать записанной строке");
    }

    @AfterEach
    void tearDown() throws Exception {
        Files.deleteIfExists(testFile);
    }
}
