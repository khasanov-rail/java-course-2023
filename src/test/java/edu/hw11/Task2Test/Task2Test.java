package edu.hw11.Task2Test;

import edu.hw11.Task2.Task2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task2Test {

    @Test
    @DisplayName("Проверка измененного метода sum в прокси-классе ArithmeticUtils")
    void testModifiedSumMethod() throws Exception {
        Task2 task2 = new Task2();
        Task2.ArithmeticUtils modifiedInstance = (Task2.ArithmeticUtils) task2.createModifiedArithmeticUtils();
        assertEquals(15, modifiedInstance.sum(5, 3), "Метод sum должен умножать значения");
    }
}
