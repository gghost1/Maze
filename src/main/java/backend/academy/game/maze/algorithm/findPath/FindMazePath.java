package backend.academy.game.maze.algorithm.findPath;

import backend.academy.exception.PathNotFoundException;
import backend.academy.game.maze.algorithm.MazeUtils;
import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.cell.Cell;
import java.util.List;

public abstract class FindMazePath extends MazeUtils {
    public abstract List<Point> apply(List<List<Cell>> maze, Point start, Point end)
        throws PathNotFoundException;
}
