package edu.project4.domain;

public class FractalImage {
    private final Pixel[] data;
    private final int width;
    private final int height;

    public FractalImage(int width, int height) {
        this.width = width;
        this.height = height;
        data = new Pixel[width * height];
        for (int i = 0; i < data.length; i++) {
            data[i] = new Pixel();
        }
    }

    public Pixel pixel(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return null;
        }
        return data[y * width + x];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
