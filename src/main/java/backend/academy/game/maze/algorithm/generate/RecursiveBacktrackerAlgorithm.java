package backend.academy.game.maze.algorithm.generate;

import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.cell.Cell;
import java.security.SecureRandom;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class RecursiveBacktrackerAlgorithm implements CreateMaze {

    private List<List<Integer>> utilMaze;

    @Override
    public List<List<Cell>> apply(List<List<Cell>> maze, Point start, Point end) {
        utilMaze = initUtilMaze((maze.getFirst().size() - 1) / 2, (maze.size() - 1) / 2);
        SecureRandom secureRandom = new SecureRandom();
        Deque<Point> points = new ArrayDeque<>();

        points.push(start);
        utilMaze.get(start.y()).set(start.x(), 0);

        while (!points.isEmpty()) {
            Point coordinatesOfStart = points.peek();
            List<Point> unvisitedNeighbors = unvisitedNeighborCells(coordinatesOfStart.x(), coordinatesOfStart.y());
            Collections.shuffle(unvisitedNeighbors);

            if (!unvisitedNeighbors.isEmpty()) {
                Point coordinatesOfNext = unvisitedNeighbors.get(secureRandom.nextInt(unvisitedNeighbors.size()));
                points.push(coordinatesOfNext);
                utilMaze.get(coordinatesOfNext.y()).set(coordinatesOfNext.x(), 0);
                connectCells(maze, coordinatesOfStart, coordinatesOfNext);
            } else {
                points.pop();
            }
        }

        return maze;
    }

    private List<Point> unvisitedNeighborCells(int x, int y) {
        List<Point> neighbors = new ArrayList<>();
        if ((x > 0) && utilMaze.get(y).get(x - 1) != 0) {
            neighbors.add(new Point(x - 1, y));
        }
        if ((y > 0) && utilMaze.get(y - 1).get(x) != 0) {
            neighbors.add(new Point(x, y - 1));
        }
        if ((x < utilMaze.getFirst().size() - 1) && utilMaze.get(y).get(x + 1) != 0) {
            neighbors.add(new Point(x + 1, y));
        }
        if ((y < utilMaze.size() - 1) && utilMaze.get(y + 1).get(x) != 0) {
            neighbors.add(new Point(x, y + 1));
        }
        return neighbors;
    }
}
