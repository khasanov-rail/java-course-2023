package edu.project4.transformation;

import edu.project4.domain.Point;

public class SwirlTransformation implements Transformation {
    @Override
    public Point transform(Point point) {
        double r2 = point.getX() * point.getX() + point.getY() * point.getY();
        double sinr2 = Math.sin(r2);
        double cosr2 = Math.cos(r2);
        double x = point.getX() * sinr2 - point.getY() * cosr2;
        double y = point.getX() * cosr2 + point.getY() * sinr2;
        return new Point(x, y);
    }
}
