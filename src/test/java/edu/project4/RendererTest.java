package edu.project4;

import edu.project4.domain.FractalImage;
import edu.project4.domain.Rect;
import edu.project4.renderer.SimpleRenderer;
import edu.project4.transformation.LinearTransformation;
import edu.project4.transformation.Transformation;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RendererTest {

    @Test
    public void testSimpleRendererRendersImage() {
        // Arrange
        FractalImage image = new FractalImage(100, 100);
        Rect world = new Rect(-1, -1, 2, 2);
        Transformation transformation = new LinearTransformation(1, 0, 0, 1, 0, 0);
        SimpleRenderer renderer = new SimpleRenderer();

        // Act
        FractalImage result =
            renderer.render(image, world, Collections.singletonList(transformation), 1000, (short) 10, 0L);

        // Assert
        assertNotNull(result, "Rendered image should not be null");

        // Проверяем, что после рендеринга в изображении есть измененные пиксели
        boolean hasChangedPixels = false;
        for (int x = 0; x < result.getWidth(); x++) {
            for (int y = 0; y < result.getHeight(); y++) {
                if (result.pixel(x, y).getHitCount() > 0) {
                    hasChangedPixels = true;
                    break;
                }
            }
            if (hasChangedPixels) {
                break;
            }
        }

        assertTrue(hasChangedPixels, "Rendered image should have some changed pixels after rendering");
    }
}
