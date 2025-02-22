package backend.academy.game.maze.algorithm;

import it.unimi.dsi.fastutil.Pair;
import java.util.Objects;

public class Point {
    protected final Pair<Integer, Integer> coordinates;

    public Point(int x, int y) {
        this.coordinates = Pair.of(x, y);
    }

    public int x() {
        return coordinates.first();
    }

    public int y() {
        return coordinates.second();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || (getClass() != o.getClass() && !(o instanceof Point))) {
            return false;
        }
        Point point = (Point) o;
        return Objects.equals(coordinates.first(), point.coordinates.first())
            && Objects.equals(coordinates.second(), point.coordinates.second());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(coordinates);
    }
}
