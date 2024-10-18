package backend.academy.game.maze.algorithm.findPath;

import backend.academy.exception.PathNotFoundException;
import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.cell.Cell;
import backend.academy.game.maze.cell.Path;
import lombok.Getter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStar implements FindMazePath {

    private List<List<Cell>> maze;

    @Override
    public List<Point> apply(List<List<Cell>> maze, Point start, Point end) throws PathNotFoundException {
        this.maze = maze;
        PointModified bestEnd = new PointModified(0, 0).setDistance(Integer.MAX_VALUE/2, Integer.MAX_VALUE/2);
        Queue<PointModified> queue = new PriorityQueue<>(
            Comparator.comparingInt((PointModified o) -> o.weight).thenComparingInt(o -> o.directDistance));

        queue.add(new PointModified(start).setDistance(directPath(start, end), 0));

        while (!queue.isEmpty()) {
            PointModified current = queue.poll();
            if (current.equals(end)) {
                if (bestEnd.weight > current.weight) {
                    bestEnd = current;
                }
                continue;
            }
            for (Point direction: directions()) {
                Point to = new Point(current.x() + direction.x(), current.y() + direction.y());

                if (isValidDestination(getRealPoint(to), maze.getFirst().size(), maze.size())
                && getRealCell(getRealX(to.x()) - direction.x(), getRealY(to.y()) - direction.y(), maze) == null
                && (current.prev() == null || !isVisited(current, to))) {
                    queue.add(
                        current
                            .createNext(to)
                            .setDistance(
                                directPath(to, end),
                                current.passedDistance + ((Path) getCell(to.x(), to.y(), maze)).florType().getValue()
                                ));
                }
            }
        }

        if (bestEnd.weight == Integer.MAX_VALUE) {
            throw new PathNotFoundException("There is no path from start to end in the maze");
        }

        return constructPath(bestEnd);
    }

    private int directPath(Point start, Point end) {
        int counter = 0;

        while (start.x() != end.x() || start.y() != end.y()) {
            if (start.x() < end.x()) {
                start = new Point(start.x() + 1, start.y());
                counter++;
            } else if (start.x() > end.x()) {
                start = new Point(start.x() - 1, start.y());
                counter++;
            }
            if (start.y() < end.y()) {
                start = new Point(start.x(), start.y() + 1);
                counter++;
            } else if (start.y() > end.y()) {
                start = new Point(start.x(), start.y() - 1);
                counter++;
            }
        }

        return counter;
    }

    private boolean isVisited(PointModified current, Point to) {
        while (true) {
            if (current.prev() == null) {
                return false;
            } else if (current.prev().equals(to)) {
                return true;
            } else {
                current = current.prev();
            }
        }
    }

    private List<Point> constructPath(PointModified end) {
        List<Point> path = new ArrayList<>();
        path.add(end);
        ((Path) getCell(end.x(), end.y(), maze)).setPath();

        while (end.prev() != null) {
            PointModified prev = end.prev();
            ((Path) getCell(prev.x(), prev.y(), maze)).setPath();
            path.add(prev);
            end = end.prev();
        }
        return path;
    }

    @Getter
    static class PointModified extends Point {
        private PointModified prev;
        private int directDistance;
        private int passedDistance;
        private int weight;

        public PointModified(Point point) {
            super(point.x(), point.y());
        }

        public PointModified(int x, int y) {
            super(x, y);
        }

        public PointModified setDistance(int directDistance, int passedDistance) {
            this.directDistance = directDistance;
            this.passedDistance = passedDistance;
            this.weight = directDistance + passedDistance;
            return this;
        }

        public PointModified createNext(Point point) {
            PointModified next = new PointModified(point);
            next.prev = this;
            return next;
        }

        @Override
        public boolean equals(Object o) {
            return super.equals(o);
        }
    }

}
