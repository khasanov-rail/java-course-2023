package edu.hw8.Task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {

    private final Thread[] threads;
    private final BlockingQueue<Runnable> taskQueue;
    private volatile boolean isRunning = true;

    public FixedThreadPool(int nThreads) {
        threads = new Thread[nThreads];
        taskQueue = new LinkedBlockingQueue<>();

        for (int i = 0; i < nThreads; i++) {
            threads[i] = new Worker();
            threads[i].start();
        }
    }

    @Override
    public void start() {
        for (Thread thread : threads) {
            if (!thread.isAlive()) {
                thread.start();
            }
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (isRunning) {
            taskQueue.offer(runnable);
        }
    }

    @Override
    public void close() {
        isRunning = false;
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private class Worker extends Thread {
        public void run() {
            while (isRunning) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
