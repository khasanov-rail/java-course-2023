package edu.project2.solver;

import edu.project2.generator.DFSGenerator;
import edu.project2.generator.Generator;
import edu.project2.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BFSSolverTest {

    @Test
    void solve_pathExists_pathFound() {
        // Arrange
        Cell[][] grid = {
            {new Cell(0, 0, CellType.PASSAGE), new Cell(0, 1, CellType.PASSAGE), new Cell(0, 2, CellType.PASSAGE)},
            {new Cell(1, 0, CellType.WALL),    new Cell(1, 1, CellType.WALL),    new Cell(1, 2, CellType.PASSAGE)},
            {new Cell(2, 0, CellType.PASSAGE), new Cell(2, 1, CellType.PASSAGE), new Cell(2, 2, CellType.PASSAGE)}
        };
        Maze maze = new Maze(3, 3, grid);
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(2, 2);
        Solver solver = new BFSSolver();

        // Act
        List<Coordinate> path = solver.solve(maze, start, end);

        // Assert
        assertNotNull(path);
        assertFalse(path.isEmpty(), "Path should not be empty");
        assertEquals(start, path.get(0), "Path should start at the start coordinate");
        assertEquals(end, path.get(path.size() - 1), "Path should end at the end coordinate");
    }


}
