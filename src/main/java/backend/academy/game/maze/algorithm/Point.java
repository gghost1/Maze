package backend.academy.game.maze.algorithm;

import it.unimi.dsi.fastutil.Pair;

public class Point {
    private final Pair<Integer, Integer> coordinates;

    public Point(int x, int y) {
        this.coordinates = Pair.of(x, y);
    }

    public Point(Pair<Integer, Integer> coordinates) {
        this.coordinates = coordinates;
    }

    public Point(Point point) {
        this.coordinates = Pair.of(point.x(), point.y());
    }

    public int x() {
        return coordinates.first();
    }

    public int y() {
        return coordinates.second();
    }
}
