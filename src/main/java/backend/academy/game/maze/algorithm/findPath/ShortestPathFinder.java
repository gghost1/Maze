package backend.academy.game.maze.algorithm.findPath;

import backend.academy.exception.PathNotFoundException;
import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.cell.Cell;
import backend.academy.game.maze.cell.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ShortestPathFinder implements FindMazePath {

    private List<List<Cell>> maze;

    @Override
    public List<Point> apply(
        List<List<Cell>> mazeInput,
        Point start,
        Point end
    ) throws PathNotFoundException {
        this.maze = mazeInput;
        Cell startCell = getCell(start.x(), start.y(), maze);
        Cell endCell = getCell(end.x(), end.y(), maze);

        Queue<Cell> queue = new LinkedList<>();
        queue.add(startCell);

        Map<Cell, Cell> predecessors = new HashMap<>();
        predecessors.put(startCell, null);

        while (!queue.isEmpty()) {
            Cell cell = queue.poll();

            if (cell.equals(endCell)) {
                return constructPath(predecessors, endCell);
            }

            List<Point> directions = directions();

            for (int i = 0; i < directions().size(); i++) {
                Point to = new Point(
                    getRealX(cell.x() + directions.get(i).x()),
                    getRealY(cell.y() + directions.get(i).y()));

                if (isValidDestination(to, maze.size(), maze.getFirst().size())
                    && moveTo(new Point(cell.x(), cell.y()), directions.get(i))) {
                    Cell destinationCell = getRealCell(to.x(), to.y(), maze);
                    if (!predecessors.containsKey(destinationCell)) {
                        queue.add(destinationCell);
                        predecessors.put(destinationCell, cell);
                    }
                }

            }
        }
        throw new PathNotFoundException("");
    }

    private boolean moveTo(Point from, Point direction) {
        int realX = getRealX(from.x());
        int realY = getRealY(from.y());

        Cell cellBetween = getRealCell(realX + direction.x(), realY + direction.y(), maze);
        return cellBetween == null;
    }



    private List<Point> constructPath(Map<Cell, Cell> predecessors, Cell end) {
        List<Point> path = new ArrayList<>();
        Cell step = end;

        while (step != null) {
            if (step instanceof Path) {
                ((Path) step).setPath();
            }
            path.add(new Point(step.x(), step.y()));
            step = predecessors.get(step);
        }
        Collections.reverse(path);
        return path;
    }


}
