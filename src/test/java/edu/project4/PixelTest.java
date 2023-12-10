package edu.project4;

import edu.project4.domain.Pixel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PixelTest {

    @Test
    public void testAddColor() {
        // Arrange
        Pixel pixel = new Pixel();
        int redToAdd = 10;
        int greenToAdd = 20;
        int blueToAdd = 30;

        // Act
        pixel.addColor(redToAdd, greenToAdd, blueToAdd);

        // Assert
        assertEquals(redToAdd, pixel.getR(), "Red component should be equal to the added value");
        assertEquals(greenToAdd, pixel.getG(), "Green component should be equal to the added value");
        assertEquals(blueToAdd, pixel.getB(), "Blue component should be equal to the added value");
        assertEquals(1, pixel.getHitCount(), "Hit count should be incremented once");
    }

    @Test
    public void testGetColor() {
        // Arrange
        Pixel pixel = new Pixel();
        pixel.addColor(255, 255, 255);

        // Act
        int color = pixel.getColor();
        int expectedColor = (255 << 16) | (255 << 8) | 255; // Expected white color

        // Assert
        assertEquals(expectedColor, color, "The color should be white when all color components are at maximum");
    }
}
