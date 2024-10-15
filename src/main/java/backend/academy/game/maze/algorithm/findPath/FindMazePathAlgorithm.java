package backend.academy.game.maze.algorithm.findPath;

public enum FindMazePathAlgorithm {
    DEAD_END_FILLER(DeadEndFiller.class),
    SHORTEST_PATH_FINDER(ShortestPathFinder.class);

    private final Class<? extends FindMazePath> algorithmClass;

    FindMazePathAlgorithm(Class<? extends FindMazePath> algorithmClass) {
        this.algorithmClass = algorithmClass;
    }

    public static FindMazePathAlgorithm valueOf(Class<? extends FindMazePath> algorithmClass) {
        return switch (algorithmClass.getSimpleName()) {
            case "DeadEndFiller" -> DEAD_END_FILLER;
            case "ShortestPathFinder" -> SHORTEST_PATH_FINDER;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case DEAD_END_FILLER -> "Dead end filler";
            case SHORTEST_PATH_FINDER -> "Shortest path finder";
        };
    }

    public Class<? extends FindMazePath> getAlgorithm() {
        return algorithmClass;
    }
}
