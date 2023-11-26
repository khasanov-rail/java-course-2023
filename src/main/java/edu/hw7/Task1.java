package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {
    private final AtomicInteger counter = new AtomicInteger();

    public void increment() {
        counter.incrementAndGet();
    }

    public void decrement() {
        counter.decrementAndGet();
    }

    public void performAction(int numberOfThreads, Runnable action) {
        if (numberOfThreads < 0) {
            throw new IllegalArgumentException("Количество потоков не может быть отрицательным");
        }

        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(action).start();
        }
    }

    public int getCounter() {
        return counter.get();
    }
}
