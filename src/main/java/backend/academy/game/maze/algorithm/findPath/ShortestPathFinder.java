package backend.academy.game.maze.algorithm.findPath;

import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.cell.Cell;
import backend.academy.game.maze.cell.Path;
import it.unimi.dsi.fastutil.Pair;
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
    public List<Pair<Integer, Integer>> apply(
        List<List<Cell>> mazeInput,
        Pair<Integer, Integer> start,
        Pair<Integer, Integer> end
    ) {
        this.maze = mazeInput;
        Cell startCell = maze.get(getY(start.second())).get(getX(start.first()));
        Cell endCell = maze.get(getY(end.second())).get(getX(end.first()));

        Queue<Cell> queue = new LinkedList<>();
        queue.add(startCell);

        Map<Cell, Cell> predecessors = new HashMap<>();
        predecessors.put(startCell, null);

        while (!queue.isEmpty()) {
            Cell cell = queue.poll();

            if (cell.equals(endCell)) {
                return constructPath(predecessors, endCell);
            }

            List<Pair<Integer, Integer>> directions = directions();

            for (int i = 0; i < directions().size(); i++) {
                Point to = new Point(cell.x() + directions.get(i).first(), cell.y() + directions.get(i).second());

                if (isValidDestination(to, maze.size(), maze.getFirst().size())
                    && moveTo(new Point(cell.x(), cell.y()), directions.get(i))) {
                    Cell destinationCell = maze.get(getY(to.y())).get(getX(to.x()));
                    if (!predecessors.containsKey(destinationCell)) {
                        queue.add(destinationCell);
                        predecessors.put(destinationCell, cell);
                    }
                }

            }
        }
        return List.of();
    }

    private boolean moveTo(Point from, Pair<Integer, Integer> direction) {
        int realX = getX(from.x());
        int realY = getY(from.y());

        Cell cellBetween = maze.get(realY+direction.second()).get(realX+direction.first());
        return cellBetween == null;
    }

    private boolean isValidDestination(Point to, int width, int height) {
        return to.x() >= 0 && to.y() >= 0 && to.x() < width && to.y() < height;
    }

    private List<Pair<Integer, Integer>> constructPath(Map<Cell, Cell> predecessors, Cell end) {
        List<Pair<Integer, Integer>> path = new ArrayList<>();
        Cell step = end;

        while (step != null) {
            if (step instanceof Path) {
                ((Path) step).setPath();
            }
            path.add(Pair.of(step.x(), step.y()));
            step = predecessors.get(step);
        }
        Collections.reverse(path);
        return path;
    }


}
