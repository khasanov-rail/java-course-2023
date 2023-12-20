package edu.project4.transformation;

import edu.project4.domain.Point;

public class HorseshoeTransformation implements Transformation {
    @Override
    public Point transform(Point point) {
        double r = Math.sqrt(point.getX() * point.getX() + point.getY() * point.getY());
        double x = (point.getX() - point.getY()) * (point.getX() + point.getY()) / r;
        double y = 2 * point.getX() * point.getY() / r;
        return new Point(x, y);
    }
}
