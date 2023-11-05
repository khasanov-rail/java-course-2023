package edu.project2;

import edu.project2.generator.DFSGenerator;
import edu.project2.generator.Generator;
import edu.project2.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DFSGeneratorTest {

    @Test
    void generate_validSize_mazeGenerated() {
        // Arrange
        int height = 10;
        int width = 10;
        Generator generator = new DFSGenerator();

        // Act
        Maze maze = generator.generate(height, width);

        // Assert
        assertNotNull(maze);
        assertEquals(height, maze.getHeight());
        assertEquals(width, maze.getWidth());
    }
}
