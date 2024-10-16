package backend.academy.game.maze.cell;

import backend.academy.exception.NotInitializedException;
import backend.academy.io.language.LanguageManager;
import it.unimi.dsi.fastutil.Pair;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Path extends Cell {
    private final CellFlorType florType;
    private final List<Pair<Integer, Integer>> next;
    private boolean isPath = false;

    public Path(int x, int y, CellFlorType florType) {
        super(x, y, CellType.PATH);
        this.florType = florType;
        this.next = new ArrayList<>();
    }

    public void setPath() {
        isPath = true;
    }

    @Override
    public String getRepresentation() throws NotInitializedException {
        return isPath ? LanguageManager.dictionary().getCorrectPath() : LanguageManager.dictionary().getPath();
    }
}
