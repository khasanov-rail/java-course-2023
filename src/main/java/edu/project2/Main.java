package edu.project2;

import edu.project2.generator.DFSGenerator;
import edu.project2.generator.Generator;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.renderer.ConsoleRenderer;
import edu.project2.renderer.Renderer;
import edu.project2.solver.BFSSolver;
import edu.project2.solver.Solver;
import java.util.List;

@SuppressWarnings("checkstyle:RegexpSinglelineJava")
public class Main {
    private static final int MAZE_WIDTH = 5;
    private static final int MAZE_HEIGHT = 5;
    private static final int START_ROW = 0;
    private static final int START_COL = 0;
    private static final int END_ROW = 4;
    private static final int END_COL = 4;

    private Main() {
    }

    public static void main(String[] args) {
        // Генерация лабиринта с использованием DFSGenerator
        Generator generator = new DFSGenerator();
        Maze maze = generator.generate(MAZE_WIDTH, MAZE_HEIGHT);

        // Указание начальной и конечной точек
        Coordinate start = new Coordinate(START_ROW, START_COL);
        Coordinate end = new Coordinate(END_ROW, END_COL);

        // Использование решателя для поиска пути
        Solver solver = new BFSSolver();
        List<Coordinate> path = solver.solve(maze, start, end);

        // Отрисовка лабиринта и пути
        Renderer renderer = new ConsoleRenderer();
        System.out.println("Лабиринт:");
        System.out.println(renderer.render(maze));

        if (!path.isEmpty()) {
            System.out.println("Путь от (" + START_ROW + "," + START_COL + ") до (" + END_ROW + "," + END_COL + "):");
            System.out.println(renderer.render(maze, path));
        } else {
            System.out.println("Путь не найден!");
        }
    }
}
