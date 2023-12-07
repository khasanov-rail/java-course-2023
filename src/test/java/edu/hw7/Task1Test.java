package edu.hw7;

import java.util.concurrent.CountDownLatch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task1Test {

    @Test
    @DisplayName("Тест увеличения счетчика в однопоточном режиме")
    void testSingleThreadIncrement() {
        // Arrange
        Task1 task = new Task1();

        // Act
        task.increment();

        // Assert
        assertEquals(1, task.getCounter(), "Счетчик должен быть увеличен до 1");
    }

    @Test
    @DisplayName("Тест увеличения счетчика в многопоточном режиме")
    void testMultiThreadIncrement() throws InterruptedException {
        // Arrange
        Task1 task = new Task1();
        int numberOfThreads = 100;
        Thread[] threads = new Thread[numberOfThreads];
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        // Act
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(() -> {
                latch.countDown();
                try {
                    latch.await();
                    task.increment();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        // Assert
        assertEquals(numberOfThreads, task.getCounter(), "Счетчик должен быть увеличен на количество потоков");
    }

    @Test
    @DisplayName("Тест увеличения счетчика в многопоточном режиме")
    void testMultiThreadIncrementAssertJ() throws InterruptedException {
        // Arrange
        Task1 task = new Task1();
        int numberOfThreads = 100;
        Thread[] threads = new Thread[numberOfThreads];
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        // Act
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(() -> {
                latch.countDown();
                try {
                    latch.await();
                    task.increment();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        // Assert
        assertThat(task.getCounter()).as("Счетчик должен быть увеличен на количество потоков")
            .isEqualTo(numberOfThreads);
    }

    @Test
    @DisplayName("Тест уменьшения счетчика в однопоточном режиме")
    void testSingleThreadDecrement() {
        // Arrange
        Task1 task = new Task1();

        // Act
        task.decrement();

        // Assert
        assertEquals(-1, task.getCounter(), "Счетчик должен быть уменьшен до -1");
    }

    @Test
    @DisplayName("Тест на достижение отрицательного значения счетчика")
    void testCounterBecomingNegative() {
        // Arrange
        Task1 task = new Task1();
        int decrementCount = 5;

        // Act
        for (int i = 0; i < decrementCount; i++) {
            task.decrement();
        }

        // Assert
        assertTrue(task.getCounter() < 0, "Счетчик должен стать отрицательным после декрементов");
    }

    @Test
    @DisplayName("Тест на исключение при отрицательном количестве потоков")
    void testNegativeThreadCount() {
        // Arrange
        Task1 task = new Task1();
        int negativeThreadCount = -1;

        // Act & Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> task.performAction(negativeThreadCount, task::increment),
            "Должно быть выброшено исключение IllegalArgumentException при отрицательном количестве потоков"
        );
    }
}
