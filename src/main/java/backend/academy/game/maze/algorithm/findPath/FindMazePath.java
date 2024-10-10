package backend.academy.game.maze.algorithm.findPath;

import backend.academy.exception.PathNotFoundException;
import backend.academy.game.maze.algorithm.MazeUtils;
import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.cell.Cell;
import it.unimi.dsi.fastutil.Pair;
import java.util.List;

public interface FindMazePath extends MazeUtils {
    List<Pair<Integer, Integer>> apply(List<List<Cell>> maze, Pair<Integer, Integer> start, Pair<Integer, Integer> end)
        throws PathNotFoundException;

    default List<Pair<Integer, Integer>> directions() {
        return List.of(
            Pair.of(0, 1),
            Pair.of(0, -1),
            Pair.of(1, 0),
            Pair.of(-1, 0)
        );
    }
    default List<Pair<Integer, Integer>> realDirections() {
        return List.of(
            Pair.of(0, 2),
            Pair.of(0, -2),
            Pair.of(2, 0),
            Pair.of(-2, 0)
        );
    }
    default boolean isValidDestination(Point to, int width, int height) {
        return to.x() > 0 && to.y() > 0 && to.x() < width-1 && to.y() < height-1;
    }
}
