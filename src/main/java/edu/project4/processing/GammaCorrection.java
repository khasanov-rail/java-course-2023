package edu.project4.processing;

import edu.project4.domain.FractalImage;
import edu.project4.domain.Pixel;

public class GammaCorrection implements ImageProcessor {
    private static final double MAX_COLOR_VALUE = 255.0;
    private final double gamma;

    public GammaCorrection(double gamma) {
        this.gamma = gamma;
    }

    @Override
    public void process(FractalImage image) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Pixel pixel = image.pixel(x, y);
                if (pixel != null) {
                    pixel.setR((int) (Math.pow(pixel.getR() / MAX_COLOR_VALUE, gamma) * MAX_COLOR_VALUE));
                    pixel.setG((int) (Math.pow(pixel.getG() / MAX_COLOR_VALUE, gamma) * MAX_COLOR_VALUE));
                    pixel.setB((int) (Math.pow(pixel.getB() / MAX_COLOR_VALUE, gamma) * MAX_COLOR_VALUE));
                }
            }
        }
    }
}
