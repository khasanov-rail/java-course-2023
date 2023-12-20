package edu.project4;

import edu.project4.domain.FractalImage;
import edu.project4.domain.Pixel;
import edu.project4.main.FractalFlameGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FractalFlameGeneratorTest {

    @Test
    @DisplayName("Fractal generation produces a non-null image with changed pixels")
    public void testFractalGeneration() {
        // Arrange
        int width = 800;
        int height = 600;

        // Act
        FractalImage image = FractalFlameGenerator.generateFractal(width, height);

        // Assert
        assertNotNull(image, "Generated fractal image should not be null");
        assertTrue(hasChangedPixels(image), "Generated fractal image should have some changed pixels");
    }

    private boolean hasChangedPixels(FractalImage image) {
        // This method checks if there are any pixels with a hit count greater than zero.
        boolean hasChanged = false;
        for (int i = 0; i < image.getWidth() * image.getHeight(); i++) {
            Pixel pixel = image.pixel(i % image.getWidth(), i / image.getWidth());
            if (pixel != null && pixel.getHitCount() > 0) {
                hasChanged = true;
                break;
            }
        }
        return hasChanged;
    }
}
