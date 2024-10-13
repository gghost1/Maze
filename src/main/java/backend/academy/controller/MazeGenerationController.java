package backend.academy.controller;

import backend.academy.exception.NotInitializedException;
import backend.academy.game.process.maze.MazeGenerationProcess;

public class MazeGenerationController extends Executable {

    private final MazeGenerationProcess mazeGenerationProcess;

    public MazeGenerationController(MazeGenerationProcess mazeGenerationProcess) throws NotInitializedException {
        this.mazeGenerationProcess = mazeGenerationProcess;
    }

    @Override
    public void execute() {
        mazeGenerationProcess.generateMaze();
        // TODO add output in MazeController
    }
}
