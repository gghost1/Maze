package backend.academy.game.maze.cell;

public abstract class Cell {
    protected final int x;
    protected final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
