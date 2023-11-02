package edu.hw3;

import edu.hw3.Task7;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {

    @Test
    public void testTreeMapWithNullKey() {
        // Arrange
        TreeMap<String, String> tree = Task7.createTreeMapWithNullKey();

        // Act
        tree.put(null, "test");
        tree.put("a", "apple");
        tree.put("b", "banana");

        // Assert
        assertThat(tree.containsKey(null)).isTrue();
        assertThat(tree.get(null)).isEqualTo("test");
    }
}
