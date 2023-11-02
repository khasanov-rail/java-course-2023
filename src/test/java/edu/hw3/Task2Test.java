package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {

    @Test
    public void testClusterizeWithSimpleClusters() {
        // Arrange
        String input = "()()()";
        List<String> expected = List.of("()", "()", "()");

        // Act
        List<String> result = Task2.clusterize(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testClusterizeWithNestedClusters() {
        // Arrange
        String input = "((()))";
        List<String> expected = List.of("((()))");

        // Act
        List<String> result = Task2.clusterize(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testClusterizeWithMixedClusters() {
        // Arrange
        String input = "((()))(())()()(()())";
        List<String> expected = List.of("((()))", "(())", "()", "()", "(()())");

        // Act
        List<String> result = Task2.clusterize(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testClusterizeWithIntersectedClusters() {
        // Arrange
        String input = "((())())(()(()()))";
        List<String> expected = List.of("((())())", "(()(()()))");

        // Act
        List<String> result = Task2.clusterize(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testClusterizeWithEmptyInput() {
        // Arrange
        String input = "";
        List<String> expected = List.of();

        // Act
        List<String> result = Task2.clusterize(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testClusterizeWithUnbalancedInput() {
        // Arrange
        String input = "(()";

        // Act and Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            Task2.clusterize(input);
        });
        assertEquals("Input string is not balanced", thrown.getMessage());
    }
}
