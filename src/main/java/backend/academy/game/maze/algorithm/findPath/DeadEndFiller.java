package backend.academy.game.maze.algorithm.findPath;

import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.cell.Cell;
import backend.academy.game.maze.cell.Path;
import backend.academy.game.maze.cell.Wall;
import it.unimi.dsi.fastutil.Pair;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DeadEndFiller implements FindMazePath {

    @Override
    public List<Pair<Integer, Integer>> apply(
        List<List<Cell>> maze,
        Pair<Integer, Integer> start,
        Pair<Integer, Integer> end
    ) {

        List<List<Cell>> mazeInWork = List.copyOf(maze);

        boolean isChanged = false;

        while (!isChanged) {
            isChanged = true;
            for (int i = 1; i < maze.size(); i+=2) {
                for (int j = 1; j < maze.get(i).size(); j+=2) {
                    Cell current = getRealCell(j, i, mazeInWork);
                    if (!Pair.of(current.x(), current.y()).equals(end)
                        && !Pair.of(current.x(), current.y()).equals(start)) {
                        if (current instanceof Path) {
                            Pair<Integer, Integer> validDirection = isDeadEnd(new Point(j, i), mazeInWork);
                            if (validDirection != null) {
                                mazeInWork
                                    .get(i+(validDirection.second()/2))
                                    .set(j+(validDirection.first()/2),
                                        new Wall(j+(validDirection.first()/2), i+(validDirection.second()/2)));
                                isChanged = false;
                            }
                        }
                    } else {
                        System.out.println();
                    }
                }
            }
        }

        return findPath(mazeInWork, start, end);
    }

    private Pair<Integer, Integer> isDeadEnd(Point cell, List<List<Cell>> maze) {
        List<Pair<Integer, Integer>> directions = realDirections();
        Pair<Integer, Integer> validDirection = null;
        int validStepCounter = 0;

        for (Pair<Integer, Integer> direction : directions) {
            Point to = new Point(cell.x() + direction.first(), cell.y() + direction.second());

            if (isValidDestination(to, maze.size(), maze.getFirst().size())
                && getRealCell(to.x()-(direction.first()/2), to.y()-(direction.second()/2), maze) == null) {
                validDirection = direction;
                validStepCounter++;
            }
        }

        return validStepCounter == 1 ? validDirection : null;
    }

    private List<Pair<Integer, Integer>> findPath(List<List<Cell>> maze, Pair<Integer, Integer> start, Pair<Integer, Integer> end) {
        Set<Pair<Integer, Integer>> path = new LinkedHashSet<>();

        Point endPoint = new Point(getRealX(end.first()), getRealY(end.second()));
        Point current = new Point(getRealX(start.first()), getRealY(start.second()));
        path.add(Pair.of(getX(current.x()), getY(current.y())));

        while (!current.equals(endPoint)) {
            boolean hasStep = false;
            for (Pair<Integer, Integer> direction : realDirections()) {
                Point to = new Point(current.x() + direction.first(), current.y() + direction.second());
                if (isValidDestination(to, maze.size(), maze.getFirst().size())) {
                    if (getRealCell(to.x()-direction.first()/2, to.y()-direction.second()/2, maze) == null) {
                        int pathSize = path.size();
                        path.add(Pair.of(getX(to.x()), getY(to.y())));
                        if (pathSize != path.size()) {
                            current = to;
                            hasStep = true;
                            break;
                        }
                    }
                }
            }
            if (!hasStep) {
                break; // exception
            }
        }

        return path.stream().toList();

    }



}
