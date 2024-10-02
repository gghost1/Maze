package backend.academy.game.maze.algorithm.generate;

import backend.academy.game.maze.cell.Cell;
import it.unimi.dsi.fastutil.Pair;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class RecursiveBacktrackerAlgorithm implements CreateMaze {

    private List<List<Integer>> utilMaze;

    @Override
    public List<List<Cell>> apply(List<List<Cell>> maze, Pair<Integer, Integer> start, Pair<Integer, Integer> end) {
        utilMaze = initUtilMaze((maze.getFirst().size() - 1) / 2, (maze.size() - 1) / 2);
        SecureRandom secureRandom = new SecureRandom();
        Stack<Pair<Integer, Integer>> cellStack = new Stack<>();

        cellStack.push(start);
        utilMaze.get(start.second()).set(start.first(), 0);

        while (!cellStack.isEmpty()) {
            Pair<Integer, Integer> coordinatesOfStart = cellStack.peek();
            List<Pair<Integer, Integer>> unvisitedNeighbors = unvisitedNeighborCells(coordinatesOfStart.first(), coordinatesOfStart.second());
            Collections.shuffle(unvisitedNeighbors);

            if (!unvisitedNeighbors.isEmpty()) {
                Pair<Integer, Integer> coordinatesOfNext = unvisitedNeighbors.get(secureRandom.nextInt(unvisitedNeighbors.size()));
                cellStack.push(coordinatesOfNext);
                utilMaze.get(coordinatesOfNext.second()).set(coordinatesOfNext.first(), 0);
                connectCells(maze, coordinatesOfStart, coordinatesOfNext);
            } else {
                cellStack.pop();
            }
        }

        return maze;
    }

    private List<Pair<Integer, Integer>> unvisitedNeighborCells(int x, int y) {
        List<Pair<Integer, Integer>> neighbors = new ArrayList<>();
        if ((x > 0) && utilMaze.get(y).get(x - 1) != 0) {
            neighbors.add(Pair.of(x - 1, y));
        }
        if ((y > 0) && utilMaze.get(y - 1).get(x) != 0) {
            neighbors.add(Pair.of(x, y - 1));
        }
        if ((x < utilMaze.getFirst().size() - 1) && utilMaze.get(y).get(x + 1) != 0) {
            neighbors.add(Pair.of(x + 1, y));
        }
        if ((y < utilMaze.size() - 1) && utilMaze.get(y + 1).get(x) != 0) {
            neighbors.add(Pair.of(x, y + 1));
        }
        return neighbors;
    }
}