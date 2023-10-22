package edu.hw2;

import edu.hw2.Task1.Expr;
import edu.hw2.Task1.Expr.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

    @Test
    void constantTest() {
        // Arrange
        Expr constant = new Constant(5);

        // Act
        double result = constant.evaluate();

        // Assert
        assertEquals(5, result, "Константа должна возвращать свое значение");
    }

    @Test
    void negateTest() {
        // Arrange
        Expr negation = new Negate(new Constant(-5));

        // Act
        double result = negation.evaluate();

        // Assert
        assertEquals(5, result, "Отрицание должно изменять знак числа");
    }

    @Test
    void exponentTest() {
        // Arrange
        Expr exponent = new Exponent(new Constant(2), new Constant(4));

        // Act
        double result = exponent.evaluate();

        // Assert
        assertEquals(16, result, "2 в степени 4 должно быть 16");
    }

    @Test
    void additionTest() {
        // Arrange
        Expr addition = new Addition(new Constant(2), new Constant(7));

        // Act
        double result = addition.evaluate();

        // Assert
        assertEquals(9, result, "Сумма 2 и 7 должна быть 9");
    }

    @Test
    void multiplicationTest() {
        // Arrange
        Expr multiplication = new Multiplication(new Constant(2), new Constant(5));

        // Act
        double result = multiplication.evaluate();

        // Assert
        assertEquals(10, result, "Произведение 2 на 5 должно быть 10");
    }
}

