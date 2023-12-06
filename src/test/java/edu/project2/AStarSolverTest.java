package edu.project2;

import edu.project2.model.Cell;
import edu.project2.model.CellType;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.solver.AStarSolver;
import edu.project2.solver.Solver;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AStarSolverTest {

    @Test
    void solve_pathExists_pathFound() {
        // Arrange
        Cell[][] grid = {
            {new Cell(0, 0, CellType.PASSAGE), new Cell(0, 1, CellType.PASSAGE), new Cell(0, 2, CellType.PASSAGE)},
            {new Cell(1, 0, CellType.WALL), new Cell(1, 1, CellType.WALL), new Cell(1, 2, CellType.PASSAGE)},
            {new Cell(2, 0, CellType.PASSAGE), new Cell(2, 1, CellType.PASSAGE), new Cell(2, 2, CellType.PASSAGE)}
        };
        Maze maze = new Maze(3, 3, grid);
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(2, 2);
        Solver solver = new AStarSolver();

        // Act
        List<Coordinate> path = solver.solve(maze, start, end);

        // Assert
        assertNotNull(path);
        assertFalse(path.isEmpty(), "Path should not be empty");
        assertEquals(start, path.get(0), "Path should start at the start coordinate");
        assertEquals(end, path.get(path.size() - 1), "Path should end at the end coordinate");
    }
}
