package backend.academy.game.process.maze;

import backend.academy.exception.PathNotFoundException;

public record MazeSolvingProcess(MazeProcess mazeProcess) {
    public void solveMaze() throws PathNotFoundException {
        mazeProcess.maze().solveMaze(mazeProcess.settings().start(), mazeProcess.settings().end());
    }
}
