package backend.academy.game.process.maze;

import backend.academy.exception.PathNotFoundException;

public class MazeSolvingProcess {
    private final MazeProcess mazeProcess;

    public MazeSolvingProcess(MazeProcess mazeProcess) {
        this.mazeProcess = mazeProcess;
    }

    public void solveMaze() throws PathNotFoundException {
        mazeProcess.maze().solveMaze(mazeProcess.settings().start(), mazeProcess.settings().end());
    }
}
