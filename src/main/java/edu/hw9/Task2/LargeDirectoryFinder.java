package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class LargeDirectoryFinder extends RecursiveTask<List<File>> {
    private static final int FILE_NUMERICAL_THRESHOLD = 1000; // Константа для порогового значения количества файлов
    private final File directory;

    public LargeDirectoryFinder(File directory) {
        this.directory = directory;
    }

    @Override
    protected List<File> compute() {
        List<File> largeDirectories = new ArrayList<>();
        List<LargeDirectoryFinder> tasks = new ArrayList<>();

        File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            return largeDirectories; // Возвращает пустой список, если директория пуста
        }

        for (File file : files) {
            if (file.isDirectory()) {
                LargeDirectoryFinder task = new LargeDirectoryFinder(file);
                task.fork(); // Асинхронно запускает новую подзадачу
                tasks.add(task);
            }
        }

        int fileCount = 0;
        for (File file : files) {
            if (file.isFile()) {
                fileCount++;
            }
        }

        for (LargeDirectoryFinder task : tasks) {
            List<File> result = task.join(); // Дожидается завершения подзадачи и получает её результат
            largeDirectories.addAll(result);
        }

        if (fileCount > FILE_NUMERICAL_THRESHOLD) {
            largeDirectories.add(directory); // Добавляет текущую директорию, если в ней более заданного числа файлов
        }

        return largeDirectories;
    }
}
