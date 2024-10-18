package backend.academy.game.maze.algorithm.findPath;

import backend.academy.exception.PathNotFoundException;
import backend.academy.game.maze.algorithm.MazeUtils;
import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.cell.Cell;
import java.util.List;

public interface FindMazePath extends MazeUtils {
    List<Point> apply(List<List<Cell>> maze, Point start, Point end)
        throws PathNotFoundException;
}
