package backend.academy.controller;

import backend.academy.exception.NotInitializedException;
import backend.academy.exception.PathNotFoundException;
import backend.academy.game.process.maze.MazeSolvingProcess;

public class MazeSolvingController extends Executable {

    private final MazeSolvingProcess mazeSolvingProcess;

    public MazeSolvingController(MazeSolvingProcess mazeSolvingProcess) throws NotInitializedException {
        this.mazeSolvingProcess = mazeSolvingProcess;
    }

    @Override
    public void execute() {
        try {
            mazeSolvingProcess.solveMaze();
        } catch (PathNotFoundException e) {

        }
        // TODO add output in MazeController
    }
}
