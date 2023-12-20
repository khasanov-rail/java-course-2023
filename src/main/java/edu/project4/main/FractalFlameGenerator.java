package edu.project4.main;

import edu.project4.domain.FractalImage;
import edu.project4.domain.Rect;
import edu.project4.processing.GammaCorrection;
import edu.project4.processing.ImageProcessor;
import edu.project4.renderer.MultiThreadRenderer;
import edu.project4.renderer.Renderer;
import edu.project4.transformation.HorseshoeTransformation;
import edu.project4.transformation.LinearTransformation;
import edu.project4.transformation.PolarTransformation;
import edu.project4.transformation.SinusoidalTransformation;
import edu.project4.transformation.SphericalTransformation;
import edu.project4.transformation.SwirlTransformation;
import edu.project4.transformation.Transformation;
import edu.project4.util.ImageFormat;
import edu.project4.util.ImageUtils;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("checkstyle:RegexpSinglelineJava")
public class FractalFlameGenerator {

    private static final double COEFFICIENT_A = 0.5;
    private static final double COEFFICIENT_B = 0.5;
    private static final double COEFFICIENT_C = 1.0;
    private static final double COEFFICIENT_D = 1.0;
    private static final int RECT_X = -2;
    private static final int RECT_Y = -2;
    private static final int RECT_WIDTH = 4;
    private static final int RECT_HEIGHT = 4;
    private static final int SAMPLES = 100000;
    private static final short ITER_PER_SAMPLE = 50;
    private static final int IMAGE_WIDTH = 800;
    private static final int IMAGE_HEIGHT = 600;
    private static final double GAMMA_VALUE = 2.2;
    private static final int THREAD_COUNT = 4;

    private FractalFlameGenerator() {
    }

    public static FractalImage generateFractal(int width, int height) {
        FractalImage image = new FractalImage(width, height);

        List<Transformation> transformations = new ArrayList<>();
        transformations.add(new LinearTransformation(COEFFICIENT_A, 0, 0, COEFFICIENT_B, COEFFICIENT_C, COEFFICIENT_D));
        transformations.add(new SphericalTransformation());
        transformations.add(new SwirlTransformation());
        transformations.add(new SinusoidalTransformation());
        transformations.add(new PolarTransformation());
        transformations.add(new HorseshoeTransformation());

        Renderer renderer = new MultiThreadRenderer(THREAD_COUNT);
        return renderer.render(
            image,
            new Rect(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT),
            transformations,
            SAMPLES,
            ITER_PER_SAMPLE,
            System.currentTimeMillis()
        );
    }

    @SuppressWarnings("checkstyle:uncommentedmain")
    public static void main(String[] args) {
        FractalImage image = generateFractal(IMAGE_WIDTH, IMAGE_HEIGHT);

        ImageProcessor gammaCorrection = new GammaCorrection(GAMMA_VALUE);
        gammaCorrection.process(image);

        try {
            ImageUtils.saveFractalImage(image, Paths.get("fractal.png"), ImageFormat.PNG);
            System.out.println("Fractal successfully saved as fractal.png");
        } catch (IOException e) {
            System.err.println("Error saving image: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
