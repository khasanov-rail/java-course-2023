package edu.hw10.Task2Test;

import edu.hw10.Task2.CacheProxy;
import edu.hw10.Task2.example.FibCalculator;
import edu.hw10.Task2.example.SimpleFibCalculator;
import java.io.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CacheProxyTest {

    @Test
    @DisplayName("Тестирование кэширования вызовов")
    public void testMethodCaching() {
        // Arrange
        FibCalculator calculator = new SimpleFibCalculator();
        FibCalculator proxyService = CacheProxy.create(calculator, FibCalculator.class);

        // Act
        long firstCallResult = proxyService.fib(5);
        long secondCallResult = proxyService.fib(5);

        // Assert
        assertEquals(
            firstCallResult,
            secondCallResult,
            "Результаты двух вызовов должны совпадать, так как они кэшируются"
        );
    }

    @Test
    @DisplayName("Тестирование сохранения кэша на диск")
    public void testPersistence() {
        // Arrange
        FibCalculator calculator = new SimpleFibCalculator();
        FibCalculator proxyService = CacheProxy.create(calculator, FibCalculator.class);

        // Act
        proxyService.fib(5);

        // Assert
        File cacheFile = new File("fib5.cache");
        assertTrue(cacheFile.exists(), "Кэш-файл должен быть создан на диске");
    }

    @Test
    @DisplayName("Тестирование обработки null аргументов")
    public void testNullArgumentHandling() {
        // Arrange
        FibCalculator service = new SimpleFibCalculator();
        FibCalculator proxyService = CacheProxy.create(service, FibCalculator.class);

        // Act & Assert
        assertDoesNotThrow(
            () -> proxyService.fib(-1),
            "Обработка нестандартных аргументов не должна приводить к исключению"
        );
    }

    @Test
    @DisplayName("Тестирование обработки нестандартных аргументов")
    public void testNonStandardArgumentHandling() {
        // Arrange
        FibCalculator service = new SimpleFibCalculator();
        FibCalculator proxyService = CacheProxy.create(service, FibCalculator.class);

        // Act & Assert
        assertDoesNotThrow(
            () -> proxyService.fib(-1),
            "Обработка нестандартных аргументов не должна приводить к исключению"
        );
    }

    @Test
    @DisplayName("Тестирование кэширования уникальных вызовов")
    public void testCachingUniqueCalls() {
        // Arrange
        FibCalculator calculator = new SimpleFibCalculator();
        FibCalculator proxy = CacheProxy.create(calculator, FibCalculator.class);

        // Act
        long resultFor5 = proxy.fib(5);
        long resultFor6 = proxy.fib(6);

        // Assert
        assertNotEquals(resultFor5, resultFor6, "Кэшированные результаты для разных аргументов должны быть разными");
    }
}
