package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task3Test {
    private Path testDir;

    @BeforeEach
    void setUp() throws Exception {
        testDir = Files.createTempDirectory("testDir");
        // Создание тестовых файлов
        Files.createFile(testDir.resolve("testFile.txt"));
        Files.createFile(testDir.resolve("test.png"));
        Files.createFile(testDir.resolve("largeFile.txt"));
        // Устанавливаем размер файла largeFile.txt больше, чем 100_000 байт
        Files.write(testDir.resolve("largeFile.txt"), new byte[100_001]);
    }

    @Test
    @DisplayName("Фильтр по размеру файла")
    void testLargerThanFilter() throws Exception {
        Task3.AbstractFilter filter = Task3.largerThan(100_000);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(testDir, filter)) {
            assertTrue(stream.iterator().hasNext(), "Должен быть файл размером больше 100_000 байт");
        }
    }

    @Test
    @DisplayName("Фильтр по расширению файла")
    void testExtensionFilter() throws Exception {
        Task3.AbstractFilter filter = Task3.extension(".png");

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(testDir, filter)) {
            assertTrue(stream.iterator().hasNext(), "Должен быть файл с расширением .png");
        }
    }

    @Test
    @DisplayName("Фильтр по регулярному выражению (полное соответствие)")
    void testRegexMatchesFilter() throws Exception {
        Task3.AbstractFilter filter = Task3.regexMatches(".*\\.png");

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(testDir, filter)) {
            assertTrue(stream.iterator().hasNext(), "Должен быть файл, соответствующий регулярному выражению .*\\.png");
        }
    }

    @Test
    @DisplayName("Фильтр по регулярному выражению (содержание)")
    void testRegexContainsFilter() throws Exception {
        Task3.AbstractFilter filter = Task3.regexContains("test");

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(testDir, filter)) {
            assertTrue(stream.iterator().hasNext(), "Должен быть файл, содержащий 'test' в имени");
        }
    }

    @AfterEach
    void tearDown() throws Exception {
        if (Files.exists(testDir)) {
            try (Stream<Path> files = Files.walk(testDir)) {
                files.sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            }
        }
    }
}
