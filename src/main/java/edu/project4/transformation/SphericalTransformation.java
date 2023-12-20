package edu.project4.transformation;

import edu.project4.domain.Point;

public class SphericalTransformation implements Transformation {
    @Override
    public Point transform(Point point) {
        double r2 = Math.pow(point.getX(), 2) + Math.pow(point.getY(), 2);
        double x = point.getX() / r2;
        double y = point.getY() / r2;
        return new Point(x, y);
    }
}
