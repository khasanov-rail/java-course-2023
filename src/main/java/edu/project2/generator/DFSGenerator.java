package edu.project2.generator;

import edu.project2.model.Cell;
import edu.project2.model.CellType;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DFSGenerator implements Generator {
    private final Random random = new Random();

    @Override
    public Maze generate(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Cell(row, col, CellType.WALL);
            }
        }

        Coordinate start = new Coordinate(random.nextInt(height), random.nextInt(width));
        generateMaze(grid, start);

        return new Maze(height, width, grid);
    }

    private void generateMaze(Cell[][] grid, Coordinate current) {
        grid[current.getRow()][current.getCol()] =
            new Cell(current.getRow(), current.getCol(), CellType.PASSAGE);

        List<Coordinate> neighbors = getUnvisitedNeighbors(grid, current);
        Collections.shuffle(neighbors);
        for (Coordinate neighbor : neighbors) {
            if (isWall(grid, neighbor)) {
                Coordinate between = new Coordinate(
                    (current.getRow() + neighbor.getRow()) / 2,
                    (current.getCol() + neighbor.getCol()) / 2
                );
                grid[between.getRow()][between.getCol()] =
                    new Cell(between.getRow(), between.getCol(), CellType.PASSAGE);
                generateMaze(grid, neighbor);
            }
        }
    }

    private List<Coordinate> getUnvisitedNeighbors(Cell[][] grid, Coordinate coordinate) {
        int row = coordinate.getRow();
        int col = coordinate.getCol();
        List<Coordinate> neighbors = new ArrayList<>();

        if (row - 2 >= 0) {
            neighbors.add(new Coordinate(row - 2, col));
        }
        if (row + 2 < grid.length) {
            neighbors.add(new Coordinate(row + 2, col));
        }
        if (col - 2 >= 0) {
            neighbors.add(new Coordinate(row, col - 2));
        }
        if (col + 2 < grid[0].length) {
            neighbors.add(new Coordinate(row, col + 2));
        }

        return neighbors;
    }

    private boolean isWall(Cell[][] grid, Coordinate coordinate) {
        return grid[coordinate.getRow()][coordinate.getCol()].getType() == CellType.WALL;
    }
}
