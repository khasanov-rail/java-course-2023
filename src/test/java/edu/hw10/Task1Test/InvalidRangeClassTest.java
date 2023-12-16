package edu.hw10.Task1Test;

import edu.hw10.Task1.InvalidRangeClass;
import edu.hw10.Task1.RandomObjectGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InvalidRangeClassTest {

    @Test
    @DisplayName("Тестирование создания объекта с невалидным диапазоном значений (min > max)")
    public void testInvalidRangeCreation() {
        // Arrange
        RandomObjectGenerator generator = new RandomObjectGenerator();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            generator.nextObject(InvalidRangeClass.class);
        }, "Должно быть исключение, если min > max");
    }
}
