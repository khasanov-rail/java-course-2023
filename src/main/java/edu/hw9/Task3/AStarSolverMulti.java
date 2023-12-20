package edu.hw9.Task3;

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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SuppressWarnings("checkstyle:RegexpSinglelineJava")
public class AStarSolverMulti {
    private static final int DIRECTIONS_COUNT = 4;
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    private ExecutorService executorService;

    public AStarSolverMulti() {
        this.executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    }

    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        Map<Coordinate, Coordinate> cameFrom = new HashMap<>();
        Map<Coordinate, Double> costSoFar = new HashMap<>();
        PriorityQueue<Coordinate> frontier = new PriorityQueue<>(
            Comparator.comparingDouble(c -> costSoFar.getOrDefault(c, Double.MAX_VALUE) + heuristic(c, end))
        );

        frontier.add(start);
        cameFrom.put(start, null);
        costSoFar.put(start, 0.0);

        try {
            while (!frontier.isEmpty()) {
                Coordinate current = frontier.poll();

                if (current.equals(end)) {
                    return reconstructPath(cameFrom, end);
                }

                List<Coordinate> neighbors = getPassageNeighbors(maze, current);
                List<Future<?>> futures = new ArrayList<>();

                for (Coordinate next : neighbors) {
                    Future<?> future = executorService.submit(() -> {
                        double newCost = costSoFar.getOrDefault(current, Double.MAX_VALUE) + 1;
                        costSoFar.compute(next, (key, currentCost) -> {
                            if (currentCost == null || newCost < currentCost) {
                                frontier.add(next);
                                cameFrom.put(next, current);
                                return newCost;
                            }
                            return currentCost;
                        });
                    });
                    futures.add(future);
                }

                for (Future<?> future : futures) {
                    future.get();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
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

            if (newRow >= 0 && newRow < maze.getHeight() && newCol >= 0 && newCol < maze.getWidth()
                && maze.getCell(new Coordinate(newRow, newCol)).getType() == CellType.PASSAGE) {
                neighbors.add(new Coordinate(newRow, newCol));
            }
        }

        return neighbors;
    }

    private double heuristic(Coordinate a, Coordinate b) {
        return Math.abs(a.getRow() - b.getRow()) + Math.abs(a.getCol() - b.getCol());
    }
}
