package edu.hw2.Task2;

public class Square extends Shape {
    private final int side;

    Square(int side) {
        this.side = side;
    }

    @Override
    double area() {
        return side * side;
    }

    Square setSide(int side) {
        return new Square(side);
    }
}
