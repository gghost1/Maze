package backend.academy.game.maze.algorithm.generate;

public enum CreateMazeAlgorithm {
    PRIMS(PrimsAlgorithm.class),
    RECURSIVE_BACKTRACKER(RecursiveBacktrackerAlgorithm.class),
    WAVE_PROPAGATION(WavePropagationAlgorithm.class);

    private final Class<? extends CreateMaze> algorithmClass;

    CreateMazeAlgorithm(Class<? extends CreateMaze> algorithmClass) {
        this.algorithmClass = algorithmClass;
    }

    public static CreateMazeAlgorithm valueOf(Class<? extends CreateMaze> algorithmClass) {
        return switch (algorithmClass.getSimpleName()) {
            case "PrimsAlgorithm" -> PRIMS;
            case "RecursiveBacktrackerAlgorithm" -> RECURSIVE_BACKTRACKER;
            case "WavePropagationAlgorithm" -> WAVE_PROPAGATION;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case PRIMS -> "Prims";
            case RECURSIVE_BACKTRACKER -> "Recursive back tracker";
            case WAVE_PROPAGATION -> "Wave propagation";
        };
    }

    public Class<? extends CreateMaze> getAlgorithm() {
        return algorithmClass;
    }
}
