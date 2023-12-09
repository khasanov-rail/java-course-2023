package edu.hw9.Task2Test;

import edu.hw9.Task2.PredicateFileFinder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PredicateFileFinderTest {

    private File testDirectory;

    @BeforeEach
    void setUp() throws IOException {
        // Создание тестовой директории
        testDirectory = Files.createTempDirectory("testDir").toFile();

        // Создание нескольких тестовых файлов
        createTestFile(testDirectory, "test1.txt", 100);
        createTestFile(testDirectory, "test2.txt", 200);
        createTestFile(testDirectory, "image.jpg", 1500);
        createTestFile(testDirectory, "document.pdf", 2500);
    }

    private void createTestFile(File directory, String fileName, int size) throws IOException {
        File newFile = new File(directory, fileName);
        byte[] data = new byte[size];
        Files.write(newFile.toPath(), data);
    }

    @AfterEach
    void tearDown() {
        // Удаление тестовой директории и всего ее содержимого
        deleteDirectory(testDirectory);
    }

    private void deleteDirectory(File directory) {
        File[] allContents = directory.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directory.delete();
    }

    @Test
    @DisplayName("Test for files with .txt extension")
    void testPredicateFileFinderForTxtFiles() {
        // Arrange
        Predicate<File> predicate = file -> file.getName().endsWith(".txt");
        PredicateFileFinder finder = new PredicateFileFinder(testDirectory, predicate);

        // Act
        ForkJoinPool pool = new ForkJoinPool();
        List<File> foundFiles = pool.invoke(finder);

        // Assert
        assertEquals(2, foundFiles.size(), "Should find exactly 2 .txt files");
    }

    @Test
    @DisplayName("Test for files larger than 1KB")
    void testPredicateFileFinderForLargeFiles() {
        // Arrange
        Predicate<File> predicate = file -> file.isFile() && file.length() > 1024; // Файлы больше 1KB
        PredicateFileFinder finder = new PredicateFileFinder(testDirectory, predicate);

        // Act
        ForkJoinPool pool = new ForkJoinPool();
        List<File> foundFiles = pool.invoke(finder);

        // Assert
        assertEquals(2, foundFiles.size(), "Should find exactly 2 files larger than 1KB");
    }

    @Test
    @DisplayName("Test for empty directory in predicate file finder")
    void testPredicateFileFinderOnEmptyDirectory() {
        // Arrange
        File emptyDirectory = new File("path/to/empty/directory"); // Путь к пустой тестовой директории
        Predicate<File> predicate = file -> file.getName().endsWith(".txt");
        PredicateFileFinder finder = new PredicateFileFinder(emptyDirectory, predicate);

        // Act
        ForkJoinPool pool = new ForkJoinPool();
        List<File> foundFiles = pool.invoke(finder);

        // Assert
        assertTrue(foundFiles.isEmpty(), "Should return an empty list for an empty directory");
    }

    @Test
    @DisplayName("Test for null directory in predicate file finder")
    void testPredicateFileFinderOnNullDirectory() {
        // Arrange
        File nullDirectory = null;
        Predicate<File> predicate = file -> file.getName().endsWith(".txt");
        PredicateFileFinder finder = new PredicateFileFinder(nullDirectory, predicate);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            ForkJoinPool pool = new ForkJoinPool();
            pool.invoke(finder);
        }, "Should throw NullPointerException for a null directory");
    }
}
