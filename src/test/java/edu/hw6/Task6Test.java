package edu.hw6;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task6Test {

    @Test
    @DisplayName("Тестирование сканирования портов")
    void testScanPorts() {
        // Act
        List<String> openPorts = Task6.scanPorts();

        // Assert
        assertNotNull(openPorts, "Список открытых портов не должен быть null");
        assertTrue(openPorts.stream().anyMatch(p -> p.contains("TCP")), "Должны быть найдены открытые TCP порты");
    }

    @Test
    @DisplayName("Проверка наличия известных сервисов")
    void testKnownServicesInScan() {
        // Act
        List<String> openPorts = Task6.scanPorts();

        // Assert
        boolean anyKnownServiceFound = openPorts.stream()
            .anyMatch(p -> p.contains("HTTP") || p.contains("SSH") || p.contains("DNS"));
        assertTrue(anyKnownServiceFound, "Должен быть найден хотя бы один известный сервис");
    }

    @Test
    @DisplayName("Проверка отсутствия недопустимых значений")
    void testNoInvalidValuesInScan() {
        // Act
        List<String> openPorts = Task6.scanPorts();

        // Assert
        assertFalse(openPorts.stream().anyMatch(p -> p.contains("INVALID")), "Не должно быть недопустимых значений");
    }
}
