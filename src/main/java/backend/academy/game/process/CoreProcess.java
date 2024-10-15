package backend.academy.game.process;

import backend.academy.controller.MazeGenerationController;
import backend.academy.controller.MazeSolvingController;
import backend.academy.exception.IllegalSettingParameter;
import backend.academy.exception.NotInitializedException;
import backend.academy.exception.PathNotFoundException;
import backend.academy.exception.UnsuccessfulPreviousProcess;
import backend.academy.game.maze.Maze;
import backend.academy.game.process.maze.MazeGenerationProcess;
import backend.academy.game.process.maze.MazeProcess;
import backend.academy.game.process.maze.MazeSolvingProcess;
import java.lang.reflect.InvocationTargetException;

public class CoreProcess {

    private final SettingsProcess settingsProcess;
    private MazeProcess maze;

    public CoreProcess(SettingsProcess settingsProcess)
        throws NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException,
            UnsuccessfulPreviousProcess
    {
        try {
            settingsProcess.isValid();
        } catch (IllegalSettingParameter e) {
            throw new UnsuccessfulPreviousProcess("", e);
        }
        this.settingsProcess = settingsProcess;
        this.maze = new MazeProcess(new Maze(
            settingsProcess.mazeWidth(),
            settingsProcess.mazeHeight(),
            settingsProcess.createMazeAlgorithm().getAlgorithm().getConstructor().newInstance(),
            settingsProcess.findMazePathAlgorithm().getAlgorithm().getConstructor().newInstance()
        ), new MazeProcess.MazeSettings(settingsProcess.start(), settingsProcess.end()));
    }

    public void createMaze() throws NotInitializedException {
        new MazeGenerationController(new MazeGenerationProcess(maze)).execute();
    }

    public void solveMaze() throws NotInitializedException {
        new MazeSolvingController(new MazeSolvingProcess(maze)).execute();
    }
}
