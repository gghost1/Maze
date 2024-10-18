package backend.academy.game.maze.cell;

import backend.academy.exception.NotInitializedException;
import backend.academy.io.language.LanguageManager;

public class Wall extends Cell {

    public Wall(int x, int y) {
        super(x, y, CellType.WALL);
    }

    @Override
    public String getRepresentation() throws NotInitializedException {
        return LanguageManager.dictionary().getWall();
    }
}
