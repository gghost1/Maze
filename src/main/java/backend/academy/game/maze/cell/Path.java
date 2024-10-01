package backend.academy.game.maze.cell;

import it.unimi.dsi.fastutil.Pair;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Path extends Cell {
    private final int x;
    private final int y;
    private final CellType type;
    private final CellFlorType florType;
    private final List<Pair<Integer, Integer>> next;
    private boolean isPath = false;

    public Path(int x, int y, CellType type, CellFlorType florType) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.florType = florType;
        this.next = new ArrayList<>();
    }

    public boolean addNext(int x, int y) {
        if (next.size() < 4) {
            next.add(Pair.of(x, y));
            return true;
        }
        return false;
    }

    public void setPath() {
        isPath = true;
    }

    public void resetPath() {
        isPath = false;
    }

}
