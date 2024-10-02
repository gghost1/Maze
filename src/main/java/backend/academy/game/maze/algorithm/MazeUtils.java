package backend.academy.game.maze.algorithm;

import backend.academy.game.maze.cell.Cell;
import java.util.List;

public interface MazeUtils {
    default int getX(int x) {
        return x*2+1;
    }
    default int getY(int y) {
        return y*2+1;
    }
    default Cell getCell(int x, int y, List<List<Cell>> maze) {
        return maze.get(getY(y)).get(getX(x));
    }
}
