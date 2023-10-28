package edu.hw2.Task2;

public class Rectangle extends Shape {
    private final int width;
    private final int height;

    Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    double area() {
        return width * height;
    }

    public Rectangle setWidth(int width) {
        return new Rectangle(width, this.height);
    }

    public Rectangle setHeight(int height) {
        return new Rectangle(this.width, height);
    }
}
