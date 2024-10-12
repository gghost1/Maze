package backend.academy.game.maze.algorithm.findPath;

public enum FindMazePathAlgorithm {
    DEAD_END_FILLER(DeadEndFiller.class),
    SHORTEST_PATH_FINDER(ShortestPathFinder.class);

    private final Class<? extends FindMazePath> algorithmClass;

    FindMazePathAlgorithm(Class<? extends FindMazePath> algorithmClass) {
        this.algorithmClass = algorithmClass;
    }

    public Class<? extends FindMazePath> getAlgorithm() {
        return algorithmClass;
    }
}
