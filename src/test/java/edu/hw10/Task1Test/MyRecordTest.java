package edu.hw10.Task1Test;

import edu.hw10.Task1.MyRecord;
import edu.hw10.Task1.RandomObjectGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyRecordTest {

    @Test
    @DisplayName("Тестирование создания объекта MyRecord")
    public void testMyRecordCreation() throws Exception {
        // Arrange
        RandomObjectGenerator generator = new RandomObjectGenerator();

        // Act
        MyRecord myRecord = generator.nextObject(MyRecord.class);

        // Assert
        assertNotNull(myRecord, "Объект MyRecord не должен быть null");
        assertNotNull(myRecord.name(), "Поле 'name' не должно быть null");
        assertTrue(myRecord.id() >= 1, "Значение 'id' должно быть больше или равно 1");
        assertTrue(
            myRecord.score() >= 0 && myRecord.score() <= 100,
            "Значение 'score' должно быть в пределах от 0 до 100"
        );
    }

    @Test
    @DisplayName("Тестирование границ значений для MyRecord")
    public void testMyRecordValueBoundaries() throws Exception {
        // Arrange
        RandomObjectGenerator generator = new RandomObjectGenerator();

        for (int i = 0; i < 100; i++) {
            // Act
            MyRecord myRecord = generator.nextObject(MyRecord.class);

            // Assert
            assertTrue(myRecord.id() >= 1, "Значение 'id' должно быть больше или равно 1");
            assertNotNull(myRecord.name(), "Поле 'name' не должно быть null");
            assertTrue(
                myRecord.score() >= 0 && myRecord.score() <= 100,
                "Значение 'score' выходит за пределы [0, 100]"
            );
        }
    }
}
