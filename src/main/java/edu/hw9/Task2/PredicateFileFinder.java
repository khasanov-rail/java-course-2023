package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class PredicateFileFinder extends RecursiveTask<List<File>> {
    private final File directory;
    private final Predicate<File> predicate;

    public PredicateFileFinder(File directory, Predicate<File> predicate) {
        this.directory = directory;
        this.predicate = predicate;
    }

    @Override
    protected List<File> compute() {
        List<File> matchingFiles = new ArrayList<>();
        List<PredicateFileFinder> tasks = new ArrayList<>();

        File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            return matchingFiles; // Возвращает пустой список, если директория пуста или ошибка при чтении
        }

        for (File file : files) {
            if (file.isDirectory()) {
                // Создаем новую задачу для поддиректории
                PredicateFileFinder task = new PredicateFileFinder(file, predicate);
                task.fork(); // Асинхронно запускаем задачу
                tasks.add(task);
            } else if (predicate.test(file)) {
                // Добавляем файл, если он удовлетворяет предикату
                matchingFiles.add(file);
            }
        }

        for (PredicateFileFinder task : tasks) {
            // Собираем результаты из поддиректорий
            List<File> result = task.join();
            matchingFiles.addAll(result);
        }

        return matchingFiles;
    }
}
