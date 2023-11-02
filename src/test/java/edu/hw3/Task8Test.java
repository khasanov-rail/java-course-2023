package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Task8Test {

    @Test
    void testBackwardlyIteration() {
        // Arrange
        List<Integer> iterableList = List.of(1, 2, 3);
        Task8.BackwardIterator<Integer> iterator = new Task8.BackwardIterator<>(iterableList);

        // Act
        List<Integer> iterations = new ArrayList<>();
        while (iterator.hasNext()) {
            iterations.add(iterator.next());
        }

        // Assert
        assertThat(iterations).isEqualTo(List.of(3, 2, 1));
    }
}
