package edu.hw7;

import edu.hw7.Task3.Person;
import edu.hw7.Task3.Task3;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task3Test {

    @Test
    @DisplayName("Добавление и поиск человека по имени")
    void testAddAndFindByName() {
        // Arrange
        Task3 database = new Task3();
        Person person = new Person(1, "Ramil Khisamov", "123 Street", "88432223355");
        database.add(person);

        // Act
        List<Person> foundPersons = database.findByName("Ramil Khisamov");

        // Assert
        assertEquals(1, foundPersons.size(), "Должен быть найден один человек");
        assertEquals(person, foundPersons.get(0), "Найденный человек должен соответствовать добавленному");
    }

    @Test
    @DisplayName("Удаление человека и проверка, что он не найден")
    void testDeletePerson() {
        // Arrange
        Task3 database = new Task3();
        Person person = new Person(2, "Alex Stalker", "123 Street", "88432223344");
        database.add(person);
        database.delete(2);

        // Act & Assert
        assertTrue(database.findByName("Alex Stalker").isEmpty(), "После удаления человек не должен находиться");
        assertTrue(database.findByAddress("123 Street").isEmpty(), "После удаления человек не должен находиться");
        assertTrue(database.findByPhone("88432223344").isEmpty(), "После удаления человек не должен находиться");
    }

    @Test
    @DisplayName("Поиск человека по несуществующему имени возвращает пустой список")
    void testFindByNameWithNonExistingName() {
        // Arrange
        Task3 database = new Task3();

        // Act
        List<Person> foundPersons = database.findByName("NonExisting Name");

        // Assert
        assertTrue(foundPersons.isEmpty(), "Должен быть возвращен пустой список для несуществующего имени");
    }

    @Test
    @DisplayName("Поиск человека по адресу возвращает корректные данные")
    void testFindByAddress() {
        // Arrange
        Task3 database = new Task3();
        Person person1 = new Person(1, "Ramil Khisamov", "45 Street Park", "8843-678-99-99");
        Person person2 = new Person(2, "Alex Stalker", "45 Street Park", "8843-678-99-99");
        database.add(person1);
        database.add(person2);

        // Act
        List<Person> foundPersons = database.findByAddress("45 Street Park");

        // Assert
        assertEquals(2, foundPersons.size(), "Должны быть найдены два человека");
        assertTrue(foundPersons.contains(person1), "Список должен содержать Ramil Khisamov");
        assertTrue(foundPersons.contains(person2), "Список должен содержать Alex Stalker");
    }
}
