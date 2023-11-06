package edu.project2.renderer;

import edu.project2.generator.DFSGenerator;
import edu.project2.generator.Generator;
import edu.project2.model.*;
import edu.project2.solver.BFSSolver;
import edu.project2.solver.Solver;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ConsoleRendererTest {

    @Test
    void render_mazeAndPath_renderedCorrectly() {
        // Arrange
        Cell[][] grid = {
            {new Cell(0, 0, CellType.PASSAGE), new Cell(0, 1, CellType.WALL), new Cell(0, 2, CellType.PASSAGE)},
            {new Cell(1, 0, CellType.WALL), new Cell(1, 1, CellType.WALL), new Cell(1, 2, CellType.PASSAGE)},
            {new Cell(2, 0, CellType.PASSAGE), new Cell(2, 1, CellType.PASSAGE), new Cell(2, 2, CellType.PASSAGE)}
        };
        Maze maze = new Maze(3, 3, grid);
        List<Coordinate> path = Arrays.asList(new Coordinate(0, 0), new Coordinate(0, 2), new Coordinate(1, 2), new Coordinate(2, 2));
        Renderer renderer = new ConsoleRenderer();

        // Act
        String renderedMaze = renderer.render(maze);
        String renderedPath = renderer.render(maze, path);

        // Assert
        assertNotNull(renderedMaze);
        assertNotNull(renderedPath);
        assertNotEquals(renderedMaze, renderedPath);
    }

}
