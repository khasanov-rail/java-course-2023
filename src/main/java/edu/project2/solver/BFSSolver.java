package edu.project2.solver;

import edu.project2.model.CellType;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFSSolver implements Solver {
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        Queue<Coordinate> queue = new LinkedList<>();
        Map<Coordinate, Coordinate> cameFrom = new HashMap<>();
        queue.add(start);
        cameFrom.put(start, null);

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            if (current.equals(end)) {
                return reconstructPath(cameFrom, start, end);
            }

            for (Coordinate neighbor : getPassageNeighbors(maze, current)) {
                if (!cameFrom.containsKey(neighbor)) {
                    queue.add(neighbor);
                    cameFrom.put(neighbor, current);
                }
            }
        }

        return Collections.emptyList();
    }

    private List<Coordinate> getPassageNeighbors(Maze maze, Coordinate coordinate) {
        int row = coordinate.getRow();
        int col = coordinate.getCol();
        List<Coordinate> neighbors = new ArrayList<>();

        if (row - 1 >= 0 && maze.getCell(new Coordinate(row - 1, col)).getType() == CellType.PASSAGE) {
            neighbors.add(new Coordinate(row - 1, col));
        }
        if (row + 1 < maze.getHeight() && maze.getCell(new Coordinate(row + 1, col)).getType() == CellType.PASSAGE) {
            neighbors.add(new Coordinate(row + 1, col));
        }
        if (col - 1 >= 0 && maze.getCell(new Coordinate(row, col - 1)).getType() == CellType.PASSAGE) {
            neighbors.add(new Coordinate(row, col - 1));
        }
        if (col + 1 < maze.getWidth() && maze.getCell(new Coordinate(row, col + 1)).getType() == CellType.PASSAGE) {
            neighbors.add(new Coordinate(row, col + 1));
        }

        return neighbors;
    }

    private List<Coordinate> reconstructPath(Map<Coordinate, Coordinate> cameFrom, Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        for (Coordinate at = end; at != null; at = cameFrom.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
