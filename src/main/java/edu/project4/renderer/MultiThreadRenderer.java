package edu.project4.renderer;

import edu.project4.domain.FractalImage;
import edu.project4.domain.Pixel;
import edu.project4.domain.Point;
import edu.project4.domain.Rect;
import edu.project4.transformation.Transformation;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SuppressWarnings("checkstyle:RegexpSinglelineJava")
public class MultiThreadRenderer implements Renderer {
    private final int threadCount;
    private static final float MAX_BRIGHTNESS = 0.6f; // Константа для яркости
    private static final float SATURATION = 1.0f; // Константа для насыщенности
    private static final float BRIGHTNESS_THRESHOLD = 0f; // Пороговое значение для яркости

    public MultiThreadRenderer(int threadCount) {
        this.threadCount = threadCount;
    }

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> transformations,
        int samples,
        short iterPerSample,
        long seed
    ) {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            futures.add(executor.submit(createTask(
                canvas, world, transformations, samples / threadCount, iterPerSample, seed + i
            )));
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        return canvas;
    }

    private Callable<Void> createTask(
        FractalImage canvas,
        Rect world,
        List<Transformation> transformations,
        int samples,
        short iterPerSample,
        long seed
    ) {
        return () -> {
            Random random = new Random(seed);
            for (int s = 0; s < samples; s++) {
                Point point = new Point(
                    random.nextDouble() * world.getWidth() + world.getX(),
                    random.nextDouble() * world.getHeight() + world.getY()
                );

                for (int j = 0; j < iterPerSample; j++) {
                    Transformation transformation = transformations.get(random.nextInt(transformations.size()));
                    point = transformation.transform(point);

                    int x = (int) ((point.getX() - world.getX()) * canvas.getWidth() / world.getWidth());
                    int y = (int) ((point.getY() - world.getY()) * canvas.getHeight() / world.getHeight());

                    if (x >= 0 && x < canvas.getWidth() && y >= 0 && y < canvas.getHeight()) {
                        synchronized (canvas) {
                            Pixel pixel = canvas.pixel(x, y);
                            if (pixel != null) {
                                Color color = getColorForPoint(j, iterPerSample);
                                pixel.addColor(color.getRed(), color.getGreen(), color.getBlue());
                            }
                        }
                    }
                }
            }
            return null;
        };
    }

    private Color getColorForPoint(int iterCount, int maxIter) {
        float hue = (float) iterCount / maxIter;
        float brightness = iterCount < maxIter ? MAX_BRIGHTNESS : BRIGHTNESS_THRESHOLD;
        return Color.getHSBColor(hue, SATURATION, brightness);
    }
}
