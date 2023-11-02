package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {

    @Test
    public void testFreqDictWithStrings() {
        // Arrange
        List<String> input = List.of("a", "bb", "a", "bb");
        Map<String, Integer> expected = Map.of("bb", 2, "a", 2);

        // Act
        Map<String, Integer> result = Task3.frequencyDict(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testFreqDictWithWords() {
        // Arrange
        List<String> input = List.of("this", "and", "that", "and");
        Map<String, Integer> expected = Map.of("that", 1, "and", 2, "this", 1);

        // Act
        Map<String, Integer> result = Task3.frequencyDict(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testFreqDictWithCyrillic() {
        // Arrange
        List<String> input = List.of("код", "код", "код", "bug");
        Map<String, Integer> expected = Map.of("код", 3, "bug", 1);

        // Act
        Map<String, Integer> result = Task3.frequencyDict(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testFreqDictWithNumbers() {
        // Arrange
        List<Integer> input = List.of(1, 1, 2, 2);
        Map<Integer, Integer> expected = Map.of(1, 2, 2, 2);

        // Act
        Map<Integer, Integer> result = Task3.frequencyDict(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testFreqDictWithEmptyList() {
        // Arrange
        List<String> input = List.of();
        Map<String, Integer> expected = Map.of();

        // Act
        Map<String, Integer> result = Task3.frequencyDict(input);

        // Assert
        assertEquals(expected, result);
    }
}
