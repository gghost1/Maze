package backend.academy.game.maze.algorithm.generate;

import backend.academy.game.maze.cell.Cell;
import it.unimi.dsi.fastutil.Pair;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimsAlgorithm implements CreateMaze {

    private List<List<Integer>> utilMaze;
    private List<Pair<Integer, Integer>> frontier;

    @Override
    public List<List<Cell>> apply(List<List<Cell>> maze, Pair<Integer, Integer> start, Pair<Integer, Integer> end) {
        utilMaze = initUtilMaze((maze.getFirst().size() - 1) / 2, (maze.size() - 1) / 2);
        frontier = new ArrayList<>();
        SecureRandom secureRandom = new SecureRandom();

        int x = start.first();
        int y = start.second();
        setInner(x, y);

        while (!frontier.isEmpty()) {
            Collections.shuffle(frontier);
            Pair<Integer, Integer> coordinatesOfFrontier = frontier.remove(secureRandom.nextInt(frontier.size()));
            List<Pair<Integer, Integer>> innerNeighbors = getInnerNeighbors(coordinatesOfFrontier.first(), coordinatesOfFrontier.second());
            Collections.shuffle(innerNeighbors);
            Pair<Integer, Integer> coordinatesOfNeighbor = innerNeighbors.get(secureRandom.nextInt(innerNeighbors.size()));
            setInner(coordinatesOfFrontier.first(), coordinatesOfFrontier.second());
            connectCells(maze, coordinatesOfFrontier, coordinatesOfNeighbor);
        }
        return maze;
    }

    private List<Pair<Integer, Integer>> getInnerNeighbors(int x, int y) {
        List<Pair<Integer, Integer>> neighbors = new ArrayList<>();
        if ((x > 0) && (utilMaze.get(y).get(x - 1) == 0)) {
                neighbors.add(Pair.of(x - 1, y));
        }
        if ((y > 0) && utilMaze.get(y - 1).get(x) == 0) {
            neighbors.add(Pair.of(x, y - 1));
        }
        if ((x < utilMaze.getFirst().size() - 1) && utilMaze.get(y).get(x + 1) == 0) {
            neighbors.add(Pair.of(x + 1, y));
        }
        if ((y < utilMaze.size() - 1) && utilMaze.get(y + 1).get(x) == 0) {
            neighbors.add(Pair.of(x, y + 1));
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
            frontier.add(Pair.of(x, y));
        }
    }
}
