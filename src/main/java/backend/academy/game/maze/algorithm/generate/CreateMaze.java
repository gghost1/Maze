package backend.academy.game.maze.algorithm.generate;

import backend.academy.game.maze.algorithm.MazeUtils;
import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.cell.Cell;
import backend.academy.game.maze.cell.CellType;
import java.util.ArrayList;
import java.util.List;

public interface CreateMaze extends MazeUtils {
    List<List<Cell>> apply(List<List<Cell>> maze, Point start, Point end);

    default List<List<Integer>> initUtilMaze(int width, int height) {
        List<List<Integer>> innerUtilMaze = new ArrayList<>(height);
        for (int i = 0; i < height; i++) {
            innerUtilMaze.add(new ArrayList<>());
            for (int j = 0; j < width; j++) {
                innerUtilMaze.get(i).add(-1);
            }
        }
        return innerUtilMaze;
    }

    default void connectCells(List<List<Cell>> maze, Point start, Point end) {
        Cell startCell = getCell(start.x(), start.y(), maze);
        Cell endCell = getCell(end.x(), end.y(), maze);
        if (startCell.type() == CellType.PATH && endCell.type() == CellType.PATH) {
            maze.get(getRealCoordinate(start.y()) - (start.y() - end.y()))
                .set(getRealCoordinate(start.x()) - (start.x() - end.x()), null);
        }
    }
}
