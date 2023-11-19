package edu.hw6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DiskMapTest {
    private DiskMap diskMap;

    @BeforeEach
    void setup() {
        diskMap = new DiskMap("mytestfile.txt");
    }

    @AfterEach
    void tearDown() {
        diskMap.clear();
    }

    @Test
    @DisplayName("Проверка функций put и get")
    void testPutAndGet() {
        diskMap.put("key", "value");
        assertEquals("value", diskMap.get("key"), "Полученное значение должно соответствовать сохраненному");
    }

    @Test
    @DisplayName("Проверка функции удаления")
    void testRemove() {
        diskMap.put("key", "value");
        diskMap.remove("key");
        assertNull(diskMap.get("key"), "После удаления ключа должно возвращаться значение null");
    }

    @Test
    @DisplayName("Проверка функции очистки")
    void testClear() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        diskMap.clear();
        assertTrue(diskMap.isEmpty(), "После очистки словарь должен быть пуст");
    }

    @ParameterizedTest
    @CsvSource({
        "key1, value1",
        "key2, value2",
        "key3, value3"
    })
    @DisplayName("Проверка множественного добавления и получения")
    void testMultiplePutsAndGets(String key, String value) {
        diskMap.put(key, value);
        assertEquals(value, diskMap.get(key), "Полученное значение должно соответствовать добавленному для ключа: " + key);
    }

    @Test
    @DisplayName("Проверка функции size")
    void testSize() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        assertEquals(2, diskMap.size(), "Размер словаря должен соответствовать количеству элементов");
    }

    @Test
    @DisplayName("Проверка наличия ключа")
    void testContainsKey() {
        diskMap.put("key", "value");
        assertTrue(diskMap.containsKey("key"), "Словарь должен содержать добавленный ключ");
    }

    @Test
    @DisplayName("Проверка наличия значения")
    void testContainsValue() {
        diskMap.put("key", "value");
        assertTrue(diskMap.containsValue("value"), "Словарь должен содержать добавленное значение");
    }
}
