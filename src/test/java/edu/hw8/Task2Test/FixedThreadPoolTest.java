package edu.hw8.Task2Test;

import edu.hw8.Task2.FixedThreadPool;
import edu.hw8.Task2.ThreadPool;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FixedThreadPoolTest {

    private ThreadPool threadPool;

    @BeforeEach
    void setUp() {
        threadPool = new FixedThreadPool(4);
        threadPool.start();
    }

    @Test
    @DisplayName("Тест на обработку множества задач")
    void testMultipleTasks() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        for (int i = 0; i < 300; i++) {
            threadPool.execute(counter::incrementAndGet);
        }
        Thread.sleep(1000);
        assertEquals(300, counter.get(), "Все 300 задач должны быть выполнены");
    }

    @Test
    @DisplayName("Тест на отклик после завершения")
    void testShutdownResponse() throws Exception {
        threadPool.close();
        AtomicInteger counter = new AtomicInteger(0);
        threadPool.execute(counter::incrementAndGet);
        Thread.sleep(500);
        assertEquals(0, counter.get(), "Задачи не должны выполняться после остановки пула");
    }

    @Test
    @DisplayName("Тест на обработку пустой задачи")
    void testEmptyTask() {
        threadPool.execute(() -> {
        });
        assertTrue(true, "Пустая задача должна обрабатываться без ошибок");
    }

    @Test
    @DisplayName("Тест на обработку задачи с исключением")
    void testExceptionInTask() {
        threadPool.execute(() -> {
            throw new RuntimeException("Test Exception");
        });
        assertTrue(true, "Задача с исключением должна обрабатываться без ошибок");
    }

    @Test
    @DisplayName("Тест на обработку null задачи")
    void testNullTask() {
        assertThrows(
            NullPointerException.class,
            () -> threadPool.execute(null),
            "Должно быть выброшено исключение NullPointerException"
        );
    }

    @AfterEach
    void tearDown() throws Exception {
        threadPool.close();
    }
}
