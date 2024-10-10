package backend.academy.game.maze.algorithm.findPath;

import backend.academy.exception.PathNotFoundException;
import backend.academy.game.maze.algorithm.MazeUtils;
import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.cell.Cell;
import java.util.List;

public interface FindMazePath extends MazeUtils {
    List<Point> apply(List<List<Cell>> maze, Point start, Point end)
        throws PathNotFoundException;

    default List<Point> directions() {
        return List.of(
            new Point(0, 1),
            new Point(0, -1),
            new Point(1, 0),
            new Point(-1, 0)
        );
    }
    default List<Point> realDirections() {
        return List.of(
            new Point(0, 2),
            new Point(0, -2),
            new Point(2, 0),
            new Point(-2, 0)
        );
    }
    default boolean isValidDestination(Point to, int width, int height) {
        return to.x() > 0 && to.y() > 0 && to.x() < width-1 && to.y() < height-1;
    }
}
