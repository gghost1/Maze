package backend.academy.game.maze.algorithm.generate;

import backend.academy.game.maze.algorithm.MazeUtils;
import backend.academy.game.maze.cell.Cell;
import it.unimi.dsi.fastutil.Pair;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public interface CreateMaze extends MazeUtils {
    List<List<Cell>> apply(List<List<Cell>> maze, Pair<Integer, Integer> start, Pair<Integer, Integer> end);
    default List<List<Integer>> initUtilMaze(int width, int height) {
        List<List<Integer>> innerUtilMaze = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            innerUtilMaze.add(new ArrayList<>());
            for (int j = 0; j < width; j++) {
                innerUtilMaze.get(i).add(-1);
            }
        }
        return innerUtilMaze;
    }
}
