package edu.project4.transformation;

import edu.project4.domain.Point;

public class SinusoidalTransformation implements Transformation {
    @Override
    public Point transform(Point point) {
        double x = Math.sin(point.getX());
        double y = Math.sin(point.getY());
        return new Point(x, y);
    }
}
