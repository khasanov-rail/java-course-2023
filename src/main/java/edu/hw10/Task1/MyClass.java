package edu.hw10.Task1;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;
import edu.hw10.Task1.annotations.NotNull;

public class MyClass {
    private int id;
    private String name;
    private double score;

    public MyClass(@Min(1) @Max(100) int id, @NotNull String name, @Min(0) @Max(10) double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "MyClass{"
            + "id=" + id
            + ", name='" + name + '\''
            + ", score=" + score
            + '}';
    }
}
