package backend.academy.game.maze.cell;

import it.unimi.dsi.fastutil.Pair;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Path extends Cell {
    private final CellType type;
    private final CellFlorType florType;
    private final List<Pair<Integer, Integer>> next;
    private boolean isPath = false;

    public Path(int x, int y, CellType type, CellFlorType florType) {
        super(x, y);
        this.type = type;
        this.florType = florType;
        this.next = new ArrayList<>();
    }

    public void setPath() {
        isPath = true;
    }
}
