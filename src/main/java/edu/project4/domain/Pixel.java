package edu.project4.domain;

public class Pixel {

    private static final int MAX_COLOR_VALUE = 255;
    private static final int COLOR_BIT_SHIFT = 16;
    private static final int GREEN_BIT_SHIFT = 8;

    private int r;
    private int g;
    private int b;
    private int hitCount;

    public Pixel() {
        this(0, 0, 0);
    }

    public Pixel(int r, int g, int b) {
        setR(r);
        setG(g);
        setB(b);
        this.hitCount = 0;
    }

    public synchronized void addColor(int r, int g, int b) {
        this.r = Math.min(MAX_COLOR_VALUE, this.r + r);
        this.g = Math.min(MAX_COLOR_VALUE, this.g + g);
        this.b = Math.min(MAX_COLOR_VALUE, this.b + b);
        hitCount++;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = Math.min(r, MAX_COLOR_VALUE);
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = Math.min(g, MAX_COLOR_VALUE);
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = Math.min(b, MAX_COLOR_VALUE);
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getColor() {
        if (hitCount == 0) {
            return 0;
        }
        return ((r / hitCount) << COLOR_BIT_SHIFT) | ((g / hitCount) << GREEN_BIT_SHIFT) | (b / hitCount);
    }
}
