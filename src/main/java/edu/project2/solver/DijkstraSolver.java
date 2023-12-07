package edu.project2.solver;

import edu.project2.model.CellType;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraSolver implements Solver {
    private static final int DIRECTIONS_COUNT = 4;

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        Map<Coordinate, Coordinate> cameFrom = new HashMap<>();
        Map<Coordinate, Double> costSoFar = new HashMap<>();
        PriorityQueue<Coordinate> frontier = new PriorityQueue<>(Comparator.comparingDouble(costSoFar::get));

        frontier.add(start);
        cameFrom.put(start, null);
        costSoFar.put(start, 0.0);

        while (!frontier.isEmpty()) {
            Coordinate current = frontier.poll();

            if (current.equals(end)) {
                return reconstructPath(cameFrom, end);
            }

            for (Coordinate next : getPassageNeighbors(maze, current)) {
                double newCost = costSoFar.get(current) + 1;
                if (!costSoFar.containsKey(next) || newCost < costSoFar.get(next)) {
                    costSoFar.put(next, newCost);
                    frontier.add(next);
                    cameFrom.put(next, current);
                }
            }
        }

        return Collections.emptyList();
    }

    private List<Coordinate> reconstructPath(Map<Coordinate, Coordinate> cameFrom, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        Coordinate current = end;
        while (current != null) {
            path.add(current);
            current = cameFrom.get(current);
        }
        Collections.reverse(path);
        return path;
    }

    private List<Coordinate> getPassageNeighbors(Maze maze, Coordinate coordinate) {
        List<Coordinate> neighbors = new ArrayList<>();
        int[] directions = new int[] {-1, 0, 1, 0, -1};

        for (int i = 0; i < DIRECTIONS_COUNT; i++) {
            int newRow = coordinate.getRow() + directions[i];
            int newCol = coordinate.getCol() + directions[i + 1];

            if (newRow >= 0 && newRow < maze.getHeight()
                && newCol >= 0 && newCol < maze.getWidth()
                && maze.getCell(new Coordinate(newRow, newCol)).getType() == CellType.PASSAGE) {
                neighbors.add(new Coordinate(newRow, newCol));
            }
        }

        return neighbors;
    }
}
