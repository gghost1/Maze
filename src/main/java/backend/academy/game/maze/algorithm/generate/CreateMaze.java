package backend.academy.game.maze.algorithm.generate;

import backend.academy.game.maze.algorithm.MazeUtils;
import backend.academy.game.maze.cell.Cell;
import it.unimi.dsi.fastutil.Pair;
import java.security.SecureRandom;
import java.util.List;

public interface CreateMaze extends MazeUtils {
    List<List<Cell>> apply(List<List<Cell>> maze, Pair<Integer, Integer> start, Pair<Integer, Integer> end);
}
