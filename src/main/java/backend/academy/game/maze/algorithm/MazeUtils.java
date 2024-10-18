package backend.academy.game.maze.algorithm;

import backend.academy.StaticVariables;
import backend.academy.game.maze.cell.Cell;
import java.util.ArrayList;
import java.util.List;

public interface MazeUtils {
    default int getRealX(int x) {
        return x * 2 + 1;
    }

    default int getRealY(int y) {
        return y * 2 + 1;
    }

    default int getX(int realX) {
        return (realX - 1) / 2;
    }

    default int getY(int realY) {
        return (realY - 1) / 2;
    }

    default Point getRealPoint(Point point) {
        return new Point(getRealX(point.x()), getRealY(point.y()));
    }
    default Point getPoint(Point realPoint) {
        return new Point(getX(realPoint.x()), getY(realPoint.y()));
    }

    default Cell getCell(int x, int y, List<List<Cell>> maze) {
        return maze.get(getRealY(y)).get(getRealX(x));
    }

    default Cell getRealCell(int x, int y, List<List<Cell>> maze) {
        return maze.get(y).get(x);
    }

    default List<Point> directions() {
        return new ArrayList<>(List.of(
            new Point(0, 1),
            new Point(0, -1),
            new Point(1, 0),
            new Point(-1, 0)
        ));
    }

    default List<Point> realDirections() {
        return new ArrayList<>(List.of(
            new Point(0, StaticVariables.REAL_STEP()),
            new Point(0, -StaticVariables.REAL_STEP()),
            new Point(StaticVariables.REAL_STEP(), 0),
            new Point(-StaticVariables.REAL_STEP(), 0)
        ));
    }

    default boolean isValidDestination(Point to, int width, int height) {
        return to.x() > 0 && to.y() > 0 && to.x() < width - 1 && to.y() < height - 1;
    }

}
