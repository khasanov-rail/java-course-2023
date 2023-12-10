package edu.project4.transformation;

import edu.project4.domain.Point;

public class PolarTransformation implements Transformation {
    @Override
    public Point transform(Point point) {
        double r = Math.sqrt(point.getX() * point.getX() + point.getY() * point.getY());
        double theta = Math.atan2(point.getY(), point.getX());
        double x = theta / Math.PI;
        double y = r - 1.0;
        return new Point(x, y);
    }
}
