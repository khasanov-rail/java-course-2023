package edu.hw9.Task2Test;

import edu.hw9.Task2.LargeDirectoryFinder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LargeDirectoryFinderTest {

    private File testDirectory;

    @BeforeEach
    void setUp() throws IOException {
        // Создание основной тестовой директории
        testDirectory = Files.createTempDirectory("largeDirTest").toFile();

        // Создание поддиректорий и файлов
        createSubDirectoryWithFiles(testDirectory, "subdir1", 500);  // Директория с 500 файлами
        createSubDirectoryWithFiles(testDirectory, "largeSubdir", 1500);  // Директория с 1500 файлами
    }

    private void createSubDirectoryWithFiles(File parentDirectory, String subDirName, int fileCount)
        throws IOException {
        File subDir = new File(parentDirectory, subDirName);
        subDir.mkdir();

        for (int i = 0; i < fileCount; i++) {
            File file = new File(subDir, "file" + i + ".txt");
            Files.createFile(file.toPath());
        }
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
    @DisplayName("Test for directory with more than 1000 files")
    void testLargeDirectoryFinderWithMoreThanThousandFiles() {
        // Arrange
        LargeDirectoryFinder finder = new LargeDirectoryFinder(testDirectory);

        // Act
        ForkJoinPool pool = new ForkJoinPool();
        List<File> largeDirectories = pool.invoke(finder);

        // Assert
        assertTrue(
            largeDirectories.contains(new File(testDirectory, "largeSubdir")),
            "The directory with more than 1000 files should be found"
        );
    }

    @Test
    @DisplayName("Test for empty directory in large directory finder")
    void testLargeDirectoryFinderOnEmptyDirectory() {
        // Arrange
        File emptyDirectory = new File("path/to/empty/directory"); // Путь к пустой тестовой директории
        LargeDirectoryFinder finder = new LargeDirectoryFinder(emptyDirectory);

        // Act
        ForkJoinPool pool = new ForkJoinPool();
        List<File> largeDirectories = pool.invoke(finder);

        // Assert
        assertTrue(largeDirectories.isEmpty(), "Should return an empty list for an empty directory");
    }

    @Test
    @DisplayName("Test for null directory in large directory finder")
    void testLargeDirectoryFinderOnNullDirectory() {
        // Arrange
        File nullDirectory = null;
        LargeDirectoryFinder finder = new LargeDirectoryFinder(nullDirectory);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            ForkJoinPool pool = new ForkJoinPool();
            pool.invoke(finder);
        }, "Should throw NullPointerException for a null directory");
    }
}
