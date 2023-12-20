package edu.project4;

import edu.project4.domain.FractalImage;
import edu.project4.domain.Pixel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FractalImageTest {

    @Test
    public void testPixelRetrievalWithinBounds() {
        // Arrange
        FractalImage image = new FractalImage(100, 100);
        int x = 50, y = 50;

        // Act
        Pixel result = image.pixel(x, y);

        // Assert
        assertNotNull(result, "Pixel retrieval within bounds should not be null");
    }

    @Test
    public void testPixelRetrievalOutsideBounds() {
        // Arrange
        FractalImage image = new FractalImage(100, 100);
        int x = 100, y = 100;

        // Act
        Pixel result = image.pixel(x, y);

        // Assert
        assertNull(result, "Pixel retrieval outside bounds should be null");
    }
}
