package edu.hw7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Task4Multi {

    private static final double MULTIPLIER_FOR_PI = 4.0;

    private Task4Multi() {
    }

    public static double calculatePi(int numberOfSimulations) {
        if (numberOfSimulations <= 0) {
            throw new IllegalArgumentException("Количество симуляций должно быть больше нуля.");
        }

        AtomicInteger circleCount = new AtomicInteger();
        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            int simulationsPerThread = (numberOfSimulations + numberOfThreads - 1) / numberOfThreads; // ceil div
            executorService.submit(() -> {
                for (int j = 0; j < simulationsPerThread; j++) {
                    double x = ThreadLocalRandom.current().nextDouble(-1, 1);
                    double y = ThreadLocalRandom.current().nextDouble(-1, 1);
                    if (x * x + y * y <= 1) {
                        circleCount.incrementAndGet();
                    }
                }
            });
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Вычисления были прерваны", e);
        }

        return MULTIPLIER_FOR_PI * circleCount.get() / numberOfSimulations;
    }
}
