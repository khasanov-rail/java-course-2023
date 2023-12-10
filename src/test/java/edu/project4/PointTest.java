package edu.project4;

import edu.project4.domain.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointTest {

    @Test
    public void testPointInitialization() {
        // Arrange
        double x = 5.0, y = -5.0;

        // Act
        Point point = new Point(x, y);

        // Assert
        assertEquals(x, point.getX(), "X coordinate should match the initialization value");
        assertEquals(y, point.getY(), "Y coordinate should match the initialization value");
    }
}
