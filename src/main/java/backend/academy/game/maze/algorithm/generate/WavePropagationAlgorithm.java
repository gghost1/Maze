package backend.academy.game.maze.algorithm.generate;

import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.cell.Cell;
import org.checkerframework.checker.units.qual.C;
import java.security.SecureRandom;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class WavePropagationAlgorithm implements CreateMaze {

    private List<List<Cell>> maze;
    private List<List<Integer>> visitedMazeCells;
    private int width;
    private int height;

    private Queue<Point> wave1;
    private Queue<Point> wave2;

    private HashMap<Point, Point> intersected;

    @Override
    public List<List<Cell>> apply(List<List<Cell>> maze, Point start, Point end) {
        this.maze = maze;
        this.width = maze.size();
        this.height = maze.getFirst().size();

        this.visitedMazeCells =
            initUtilMaze(width, height);

        this.wave1 = new ArrayDeque<>();
        this.wave2 = new ArrayDeque<>();
        wave1.add(start);
        wave2.add(end);

        this.intersected = new HashMap<>();
        Point current;
        while (!wave1.isEmpty() && !wave2.isEmpty()) {
            current = wave1.poll();
            step(current, 1);

            current = wave2.poll();
            step(current, 2);
        }

        List<Map.Entry<Point, Point>> intersectedList = new ArrayList<>(intersected.entrySet());
        Collections.shuffle(intersectedList);

        if (intersectedList.size() > 2) {
            int i = 0;
            while (i < 2 && i < intersectedList.size()) {
                Map.Entry<Point, Point> entry = intersectedList.remove(new SecureRandom().nextInt(intersectedList.size()));
                connectCells(maze, entry.getKey(), entry.getValue());
                i++;
            }
        }

        return maze;
    }

    private void step(Point current, int waveNumber) {
        List<Point> directions = directions();
        Collections.shuffle(directions);
        for (Point direction : directions) {
            Point to = new Point(current.x() + direction.x(), current.y() + direction.y());
            if (isValidDestination(getRealPoint(to), width, height)) {
                int cellState = visitedMazeCells.get(getRealY(to.y())).get(getRealX(to.x()));
                if (cellState == -1) {
                    connectCells(maze, current, to);

                    visitedMazeCells
                        .get(getRealY(to.y()))
                        .set(getRealX(to.x()), waveNumber);
                    if (waveNumber == 1) {
                        wave1.add(to);
                    } else if (waveNumber == 2) {
                        wave2.add(to);
                    }
                } else if (cellState != waveNumber
                && !((intersected.get(to) != null && intersected.get(to).equals(current)) || (intersected.get(current) != null && intersected.get(current).equals(to)))) {
                    if (this.intersected.containsKey(current)) {
                        intersected.put(to, current);
                    } else {
                        intersected.put(current, to);
                    }
                }
            }
        }
    }

}
