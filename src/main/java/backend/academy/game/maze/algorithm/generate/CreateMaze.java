package backend.academy.game.maze.algorithm.generate;

import backend.academy.game.maze.algorithm.MazeUtils;
import backend.academy.game.maze.cell.Cell;
import backend.academy.game.maze.cell.Path;
import it.unimi.dsi.fastutil.Pair;
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
    default void connectCells(List<List<Cell>> maze, Pair<Integer, Integer> start, Pair<Integer, Integer> end) {
        Cell startCell = getCell(start.first(), start.second(), maze);
        Cell endCell = getCell(end.first(), end.second(), maze);
        if (startCell instanceof Path && endCell instanceof Path) {
            ((Path) startCell).addNext(end.first(), end.second());
            ((Path) endCell).addNext(start.first(), start.second());
            maze.get(getY(start.second()) - (start.second() - end.second()))
                .set(getX(start.first()) - (start.first() - end.first()), null);
        }
    }
}
