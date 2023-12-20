package edu.hw10.Task1;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;
import edu.hw10.Task1.annotations.NotNull;

public record MyRecord(@Min(1) int id, @NotNull String name, @Min(0) @Max(100) double score) {
}
