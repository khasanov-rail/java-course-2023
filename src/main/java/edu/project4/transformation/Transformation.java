package edu.project4.transformation;

import edu.project4.domain.Point;

@FunctionalInterface
public interface Transformation {
    Point transform(Point point);
}
