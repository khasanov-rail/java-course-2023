package edu.hw10.Task1Test;

import edu.hw10.Task1.MyClass;
import edu.hw10.Task1.RandomObjectGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomObjectGeneratorTest {

    @Test
    @DisplayName("Тестирование генерации объекта MyClass")
    public void testGenerationOfMyClass() throws Exception {
        // Arrange
        RandomObjectGenerator generator = new RandomObjectGenerator();

        // Act
        MyClass myClass = generator.nextObject(MyClass.class);

        // Assert
        assertNotNull(myClass, "Объект MyClass не должен быть null");
        assertTrue(
            myClass.getId() >= 1 && myClass.getId() <= 100,
            "Значение 'id' должно соответствовать ограничениям @Min и @Max"
        );
        assertNotNull(myClass.getName(), "Поле 'name' не должно быть null (ограничение @NotNull)");
        assertTrue(
            myClass.getScore() >= 0 && myClass.getScore() <= 10,
            "Значение 'score' должно соответствовать ограничениям @Min и @Max"
        );
    }

    @Test
    @DisplayName("Тестирование генерации объекта с несуществующим классом")
    public void testGenerationWithNonExistentClass() {
        // Arrange
        RandomObjectGenerator generator = new RandomObjectGenerator();

        // Act & Assert
        assertThrows(Exception.class, () -> {
            generator.nextObject(null);
        }, "Должно быть исключение при попытке создать объект несуществующего класса");
    }

    @Test
    @DisplayName("Тестирование обработки @NotNull аннотации")
    public void testNotNullAnnotationHandling() throws Exception {
        // Arrange
        RandomObjectGenerator generator = new RandomObjectGenerator();

        // Act
        MyClass myClass = generator.nextObject(MyClass.class);

        // Assert
        assertNotNull(myClass.getName(), "Поле 'name', помеченное @NotNull, не должно быть null");
        // Проверьте здесь другие поля, помеченные как @NotNull
    }

    @Test
    @DisplayName("Тестирование генерации не пустых строковых значений")
    public void testStringFieldGeneration() throws Exception {
        // Arrange
        RandomObjectGenerator generator = new RandomObjectGenerator();

        // Act
        MyClass myClass = generator.nextObject(MyClass.class);

        // Assert
        assertFalse(myClass.getName().isEmpty(), "Строковое поле 'name' не должно быть пустым");
    }
}
