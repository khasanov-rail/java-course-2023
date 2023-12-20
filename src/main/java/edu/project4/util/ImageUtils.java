package edu.project4.util;

import edu.project4.domain.FractalImage;
import edu.project4.domain.Pixel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public class ImageUtils {

    private ImageUtils() {
    }

    public static void saveFractalImage(FractalImage image, Path path, ImageFormat format) throws IOException {
        BufferedImage bufferedImage =
            new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Pixel pixel = image.pixel(x, y);
                if (pixel != null) {
                    bufferedImage.setRGB(x, y, pixel.getColor());
                }
            }
        }

        File outputFile = path.toFile();
        ImageIO.write(bufferedImage, format.name(), outputFile);
    }
}
