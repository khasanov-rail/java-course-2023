package edu.project4;

import edu.project4.domain.Point;
import edu.project4.transformation.LinearTransformation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransformationTest {

    @Test
    public void testLinearTransformation() {
        // Arrange
        double a = 1, b = 0, c = 0, d = 1, e = 0, f = 0;
        LinearTransformation transformation = new LinearTransformation(a, b, c, d, e, f);
        Point originalPoint = new Point(1, 1);

        // Act
        Point transformedPoint = transformation.transform(originalPoint);

        // Assert
        assertEquals(
            originalPoint.getX(),
            transformedPoint.getX(),
            "X coordinate should not change after transformation"
        );
        assertEquals(
            originalPoint.getY(),
            transformedPoint.getY(),
            "Y coordinate should not change after transformation"
        );
    }
}
