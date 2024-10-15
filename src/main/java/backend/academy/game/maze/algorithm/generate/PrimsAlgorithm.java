package backend.academy.game.maze.algorithm.generate;

import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.cell.Cell;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimsAlgorithm implements CreateMaze {

    private List<List<Integer>> utilMaze;
    private List<Point> frontier;

    @Override
    public List<List<Cell>> apply(List<List<Cell>> maze, Point start, Point end) {
        utilMaze = initUtilMaze((maze.getFirst().size() - 1) / 2, (maze.size() - 1) / 2);
        frontier = new ArrayList<>();
        SecureRandom secureRandom = new SecureRandom();

        int x = start.x();
        int y = start.y();
        setInner(x, y);

        while (!frontier.isEmpty()) {
            Collections.shuffle(frontier);
            Point coordinatesOfFrontier = frontier.remove(secureRandom.nextInt(frontier.size()));
            List<Point> innerNeighbors = getInnerNeighbors(coordinatesOfFrontier.x(), coordinatesOfFrontier.y());
            Collections.shuffle(innerNeighbors);
            Point coordinatesOfNeighbor = innerNeighbors.get(secureRandom.nextInt(innerNeighbors.size()));
            setInner(coordinatesOfFrontier.x(), coordinatesOfFrontier.y());
            connectCells(maze, coordinatesOfFrontier, coordinatesOfNeighbor);
        }
        return maze;
    }

    private List<Point> getInnerNeighbors(int x, int y) {
        List<Point> neighbors = new ArrayList<>();
        if ((x > 0) && (utilMaze.get(y).get(x - 1) == 0)) {
                neighbors.add(new Point(x - 1, y));
        }
        if ((y > 0) && utilMaze.get(y - 1).get(x) == 0) {
            neighbors.add(new Point(x, y - 1));
        }
        if ((x < utilMaze.getFirst().size() - 1) && utilMaze.get(y).get(x + 1) == 0) {
            neighbors.add(new Point(x + 1, y));
        }
        if ((y < utilMaze.size() - 1) && utilMaze.get(y + 1).get(x) == 0) {
            neighbors.add(new Point(x, y + 1));
        }
        return neighbors;
    }

    private void setInner(int x, int y) {
        utilMaze.get(y).set(x, 0);
        if (x > 0) {
            setFrontier(x - 1, y);
        }
        if (y > 0) {
            setFrontier(x, y - 1);
        }
        if (x < utilMaze.getFirst().size() - 1) {
            setFrontier(x + 1, y);
        }
        if (y < utilMaze.size() - 1) {
            setFrontier(x, y + 1);
        }
    }

    private void setFrontier(int x, int y) {
        if (utilMaze.get(y).get(x) == -1) {
            utilMaze.get(y).set(x, 1);
            frontier.add(new Point(x, y));
        }
    }
}
