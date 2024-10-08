package backend.academy.game.maze.algorithm.findPath;

import backend.academy.game.maze.algorithm.MazeUtils;
import backend.academy.game.maze.cell.Cell;
import it.unimi.dsi.fastutil.Pair;
import java.util.List;

public interface FindMazePath extends MazeUtils {
    List<Pair<Integer, Integer>> apply(List<List<Cell>> maze, Pair<Integer, Integer> start, Pair<Integer, Integer> end);

    default List<Pair<Integer, Integer>> directions() {
        return List.of(
            Pair.of(0, 1),
            Pair.of(0, -1),
            Pair.of(1, 0),
            Pair.of(-1, 0)
        );
    }
}
