package backend.academy.game.maze.cell;

import backend.academy.exception.NotInitializedException;
import java.util.Objects;
import lombok.Getter;

@Getter
public abstract class Cell {
    protected final int x;
    protected final int y;
    protected final CellType type;

    public Cell(int x, int y, CellType type) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public abstract String getRepresentation() throws NotInitializedException;

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
