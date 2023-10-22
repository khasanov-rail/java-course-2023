package edu.hw2.Task2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    @Test void rectangleAreaTest() {
        // Arrange
        Rectangle rectangle = new Rectangle(20, 10);

        // Act
        double area = rectangle.area();

        // Assert
        assertEquals(200.0, area, "Площадь прямоугольника должна быть 200.0");
    }

    @Test void squareAreaTest() {
        // Arrange
        Square square = new Square(10);

        // Act
        double area = square.area();

        // Assert
        assertEquals(100.0, area, "Площадь квадрата должна быть 100.0");
    }

    @Test void changeRectangleDimensionsTest() {
        // Arrange
        Rectangle rectangle = new Rectangle(20, 10).setWidth(30).setHeight(15);

        // Act
        double area = rectangle.area();

        // Assert
        assertEquals(450.0, area, "Площадь прямоугольника должна быть 450.0 после изменения размеров");
    }

    @Test void changeSquareDimensionsTest() {
        // Arrange
        Square square = new Square(10).setSide(20);

        // Act
        double area = square.area();

        // Assert: Проверяем, что площадь измененного квадрата правильная
        assertEquals(400.0, area, "Площадь квадрата должна быть 400.0 после изменения размеров");
    }
}
