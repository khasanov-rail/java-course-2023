package edu.project2.renderer;

import edu.project2.model.Cell;
import edu.project2.model.CellType;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.Collections;
import java.util.List;

public class ConsoleRenderer implements Renderer {
    @Override
    public String render(Maze maze) {
        return render(maze, Collections.emptyList());
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < maze.getHeight(); row++) {
            for (int col = 0; col < maze.getWidth(); col++) {
                Coordinate coordinate = new Coordinate(row, col);
                Cell currentCell = maze.getCell(coordinate);
                if (currentCell.getType() == CellType.WALL) {
                    sb.append("#");
                } else if (path.contains(coordinate)) {
                    sb.append("·");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }
}
