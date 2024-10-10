package backend.academy.game.maze.algorithm.findPath;

import backend.academy.exception.PathNotFoundException;
import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.cell.Cell;
import backend.academy.game.maze.cell.Path;
import backend.academy.game.maze.cell.Wall;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DeadEndFiller implements FindMazePath {

    @Override
    public List<Point> apply(
        List<List<Cell>> maze,
        Point start,
        Point end
    ) throws PathNotFoundException {

        List<List<Cell>> mazeInWork = List.copyOf(maze);

        boolean isChanged = false;

        while (!isChanged) {
            isChanged = true;
            for (int i = 1; i < maze.size(); i+=2) {
                for (int j = 1; j < maze.get(i).size(); j+=2) {
                    Cell current = getRealCell(j, i, mazeInWork);
                    if (!new Point(current.x(), current.y()).equals(end)
                        && !new Point(current.x(), current.y()).equals(start)) {
                        if (current instanceof Path) {
                            Point validDirection = isDeadEnd(new Point(j, i), mazeInWork);
                            if (validDirection != null) {
                                mazeInWork
                                    .get(i+(validDirection.y()/2))
                                    .set(j+(validDirection.x()/2),
                                        new Wall(j+(validDirection.x()/2), i+(validDirection.y()/2)));
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

    private Point isDeadEnd(Point cell, List<List<Cell>> maze) {
        List<Point> directions = realDirections();
        Point validDirection = null;
        int validStepCounter = 0;

        for (Point direction : directions) {
            Point to = new Point(cell.x() + direction.x(), cell.y() + direction.y());

            if (isValidDestination(to, maze.size(), maze.getFirst().size())
                && getRealCell(to.x()-(direction.x()/2), to.y()-(direction.y()/2), maze) == null) {
                validDirection = direction;
                validStepCounter++;
            }
        }

        return validStepCounter == 1 ? validDirection : null;
    }

    private List<Point> findPath(List<List<Cell>> maze, Point start, Point end) throws PathNotFoundException {
        Set<Point> path = new LinkedHashSet<>();

        Point endPoint = new Point(getRealX(end.x()), getRealY(end.y()));
        Point current = new Point(getRealX(start.x()), getRealY(start.y()));
        path.add(new Point(getX(current.x()), getY(current.y())));

        while (!current.equals(endPoint)) {
            boolean hasStep = false;
            for (Point direction : realDirections()) {
                Point to = new Point(current.x() + direction.x(), current.y() + direction.y());
                if (isValidDestination(to, maze.size(), maze.getFirst().size())) {
                    if (getRealCell(to.x()-direction.x()/2, to.y()-direction.y()/2, maze) == null) {
                        int pathSize = path.size();
                        path.add(new Point(getX(to.x()), getY(to.y())));
                        if (pathSize != path.size()) {
                            current = to;
                            hasStep = true;
                            break;
                        }
                    }
                }
            }
            if (!hasStep) {
                throw new PathNotFoundException(""); // exception
            }
        }

        return path.stream().toList();

    }



}
