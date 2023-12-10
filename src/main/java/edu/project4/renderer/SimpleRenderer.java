package edu.project4.renderer;

import edu.project4.domain.FractalImage;
import edu.project4.domain.Point;
import edu.project4.domain.Rect;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.Random;

public class SimpleRenderer implements Renderer {
    private static final int MAX_COLOR_VALUE = 255; // Константа для максимального значения цвета

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> transformations,
        int samples,
        short iterPerSample,
        long seed
    ) {
        Random random = new Random(seed);
        for (int s = 0; s < samples; s++) {
            Point point = new Point(
                random.nextDouble() * world.getWidth() + world.getX(),
                random.nextDouble() * world.getHeight() + world.getY()
            );

            for (int j = 0; j < iterPerSample; j++) {
                Transformation transformation = transformations.get(random.nextInt(transformations.size()));
                point = transformation.transform(point);

                if (world.contains(point)) {
                    int x = (int) ((point.getX() - world.getX()) * canvas.getWidth() / world.getWidth());
                    int y = (int) ((point.getY() - world.getY()) * canvas.getHeight() / world.getHeight());
                    canvas.pixel(x, y).addColor(MAX_COLOR_VALUE, MAX_COLOR_VALUE, MAX_COLOR_VALUE);
                }
            }
        }
        return canvas;
    }
}
