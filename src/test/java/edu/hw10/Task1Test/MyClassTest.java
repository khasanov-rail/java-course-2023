package edu.hw10.Task1Test;

import edu.hw10.Task1.MyClass;
import edu.hw10.Task1.RandomObjectGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyClassTest {

    @Test
    @DisplayName("Тестирование создания объекта MyClass")
    public void testMyClassCreation() throws Exception {
        // Arrange
        RandomObjectGenerator generator = new RandomObjectGenerator();

        // Act
        MyClass myClass = generator.nextObject(MyClass.class);

        // Assert
        assertNotNull(myClass, "Объект MyClass не должен быть null");
        assertNotNull(myClass.getName(), "Поле 'name' не должно быть null");
        assertTrue(myClass.getId() >= 1 && myClass.getId() <= 100, "Значение 'id' должно быть в пределах от 1 до 100");
        assertTrue(
            myClass.getScore() >= 0 && myClass.getScore() <= 10,
            "Значение 'score' должно быть в пределах от 0 до 10"
        );
    }

    @Test
    @DisplayName("Тестирование границ значений для MyClass")
    public void testMyClassValueBoundaries() throws Exception {
        // Arrange
        RandomObjectGenerator generator = new RandomObjectGenerator();

        for (int i = 0; i < 100; i++) {
            // Act
            MyClass myClass = generator.nextObject(MyClass.class);

            // Assert
            assertTrue(myClass.getId() >= 1 && myClass.getId() <= 100, "Значение 'id' выходит за пределы [1, 100]");
            assertNotNull(myClass.getName(), "Поле 'name' не должно быть null");
            assertTrue(
                myClass.getScore() >= 0 && myClass.getScore() <= 10,
                "Значение 'score' выходит за пределы [0, 10]"
            );
        }
    }

    @Test
    @DisplayName("Тестирование поведения при создании MyClass с некорректными данными")
    public void testMyClassWithInvalidData() {
        // Arrange
        RandomObjectGenerator generator = new RandomObjectGenerator();

        // Act & Assert
        assertThrows(Exception.class, () -> {
            MyClass myClass = generator.nextObject(null);
        }, "Должно быть исключение при передаче null в качестве класса");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 50, 100})
    @DisplayName("Тестирование MyClass с разными корректными значениями 'id'")
    public void testMyClassWithVariousValidIds(int validId) throws Exception {
        // Arrange
        RandomObjectGenerator generator = new RandomObjectGenerator();

        // Act
        MyClass myClass = generator.nextObject(MyClass.class);
        myClass.setId(validId);

        // Assert
        assertEquals(validId, myClass.getId(), "Значение 'id' должно соответствовать установленному значению");
    }
}
