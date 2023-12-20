package edu.hw10.Task1;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;

public class InvalidRangeClass {
    private int value;

    public InvalidRangeClass(@Min(10) @Max(5) int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
