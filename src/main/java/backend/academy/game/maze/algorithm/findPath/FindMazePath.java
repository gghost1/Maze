package backend.academy.game.maze.algorithm.findPath;

import backend.academy.game.maze.cell.Cell;
import it.unimi.dsi.fastutil.Pair;
import java.util.List;

public interface FindMazePath {
    List<Pair<Integer, Integer>> apply(List<List<Cell>> maze);
}
