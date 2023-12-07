package edu.hw7;

import edu.hw7.Task3_5.Person;
import edu.hw7.Task3_5.Task35;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task35Test {

    @Test
    @DisplayName("Добавление и поиск человека по имени")
    void testAddAndFindByName() {
        // Arrange
        Task35 database = new Task35();
        Person person = new Person(1, "John Johnson", "12 Bauman Street", "8843-232-23-23");
        database.add(person);

        // Act
        List<Person> foundPersons = database.findByName("John Johnson");

        // Assert
        assertEquals(1, foundPersons.size(), "Должен быть найден один человек");
        assertEquals(person, foundPersons.get(0), "Найденный человек должен соответствовать добавленному");
    }

    @Test
    @DisplayName("Удаление человека и проверка, что он не найден")
    void testDeletePerson() {
        // Arrange
        Task35 database = new Task35();
        Person person = new Person(2, "Janny Johnson", "12 Bauman Street", "8843-232-23-23");
        database.add(person);
        database.delete(2);

        // Act & Assert
        assertTrue(database.findByName("Janny Johnson").isEmpty(), "После удаления человек не должен находиться");
        assertTrue(database.findByAddress("12 Bauman Street").isEmpty(), "После удаления человек не должен находиться");
        assertTrue(database.findByPhone("8843-232-23-23").isEmpty(), "После удаления человек не должен находиться");
    }

    @Test
    @DisplayName("Многопоточное добавление и поиск")
    void testConcurrentAddAndFind() throws InterruptedException {
        // Arrange
        Task35 database = new Task35();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Act
        for (int i = 0; i < 100; i++) {
            int id = i;
            executor.submit(() -> database.add(new Person(id, "Человек " + id, "адрес " + id, "номер телефона " + id)));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        // Assert
        for (int i = 0; i < 100; i++) {
            List<Person> foundPersons = database.findByName("Человек " + i);
            assertEquals(1, foundPersons.size(), "Должен быть найден один человек для каждого имени");
        }
    }
}
