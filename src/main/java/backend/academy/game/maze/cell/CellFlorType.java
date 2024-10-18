package backend.academy.game.maze.cell;

public enum CellFlorType {
    DESERT(2),
    DIRT (1),
    TRAIL (0),
    ROAD (-1);

    final int value;

    CellFlorType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return switch (this) {
            case DESERT -> "\uD83C\uDFDC\uFE0F";
            case DIRT -> "\uD83D\uDFEB";
            case TRAIL -> "\uD83D\uDFE7";
            case ROAD -> "\uD83D\uDEE3\uFE0F";
        };
    }
}
