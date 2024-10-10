package backend.academy.game.maze.algorithm.findPath;

import backend.academy.exception.PathNotFoundException;
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
    ) throws PathNotFoundException {
        this.maze = mazeInput;
        Cell startCell = getCell(start.first(), start.second(), maze);
        Cell endCell = getCell(end.first(), end.second(), maze);

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
                Point to = new Point(getRealX(cell.x() + directions.get(i).first()), getRealY(cell.y() + directions.get(i).second()));

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

    private boolean moveTo(Point from, Pair<Integer, Integer> direction) {
        int realX = getRealX(from.x());
        int realY = getRealY(from.y());

        Cell cellBetween = getRealCell(realX+direction.first(), realY+direction.second(), maze);
        return cellBetween == null;
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
