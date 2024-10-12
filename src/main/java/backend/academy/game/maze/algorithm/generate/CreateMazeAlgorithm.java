package backend.academy.game.maze.algorithm.generate;

public enum CreateMazeAlgorithm {
    PRIMS(PrimsAlgorithm.class),
    RECURSIVE_BACKTRACKER(RecursiveBacktrackerAlgorithm.class);

    private final Class<? extends CreateMaze> algorithmClass;

    CreateMazeAlgorithm(Class<? extends CreateMaze> algorithmClass) {
        this.algorithmClass = algorithmClass;
    }

    public Class<? extends CreateMaze> getAlgorithmClass() {
        return algorithmClass;
    }
}
