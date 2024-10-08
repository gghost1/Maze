package backend.academy.game.maze.algorithm;

import backend.academy.game.maze.cell.Cell;
import java.util.List;

public interface MazeUtils {
    default int getRealX(int x) {
        return x*2+1;
    }
    default int getRealY(int y) {
        return y*2+1;
    }
    default int getX(int realX) {
        return (realX-1)/2;
    }
    default int getY(int realY) {
        return (realY-1)/2;
    }
    default Cell getCell(int x, int y, List<List<Cell>> maze) {
        return maze.get(getRealY(y)).get(getRealX(x));
    }
    default Cell getRealCell(int x, int y, List<List<Cell>> maze) {
        return maze.get(y).get(x);
    }
}
