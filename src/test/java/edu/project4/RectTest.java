package edu.project4;

import edu.project4.domain.Point;
import edu.project4.domain.Rect;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RectTest {

    @Test
    public void testRectContainsPointInside() {
        // Arrange
        Rect rect = new Rect(0, 0, 10, 10);
        Point insidePoint = new Point(5, 5);

        // Act & Assert
        assertTrue(rect.contains(insidePoint), "Rect should contain a point inside its bounds");
    }

    @Test
    public void testRectDoesNotContainPointOutside() {
        // Arrange
        Rect rect = new Rect(0, 0, 10, 10);
        Point outsidePoint = new Point(15, 15);

        // Act & Assert
        assertFalse(rect.contains(outsidePoint), "Rect should not contain a point outside its bounds");
    }
}
