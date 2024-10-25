package backend.academy.game.process;

import backend.academy.controller.MazeGenerationController;
import backend.academy.controller.MazeSolvingController;
import backend.academy.exception.IllegalSettingParameterException;
import backend.academy.exception.NotInitializedException;
import backend.academy.exception.UnsuccessfulPreviousProcessException;
import backend.academy.game.maze.Maze;
import backend.academy.game.process.maze.MazeGenerationProcess;
import backend.academy.game.process.maze.MazeProcess;
import backend.academy.game.process.maze.MazeSolvingProcess;
import java.lang.reflect.InvocationTargetException;

public class CoreProcess {

    private final SettingsProcess settingsProcess;
    private MazeProcess maze;

    public static CoreProcess create(SettingsProcess settingsProcess)
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        try {
            settingsProcess.isValid();
        } catch (IllegalSettingParameterException e) {
            throw new UnsuccessfulPreviousProcessException("", e);
        }
        Maze creationMaze = new Maze(
            settingsProcess.mazeWidth(),
            settingsProcess.mazeHeight(),
            settingsProcess.createMazeAlgorithm().getAlgorithm().getConstructor().newInstance(),
            settingsProcess.findMazePathAlgorithm().getAlgorithm().getConstructor().newInstance()
        );
        return new CoreProcess(settingsProcess, creationMaze);
    }

    private CoreProcess(SettingsProcess settingsProcess, Maze maze) {
        this.settingsProcess = settingsProcess;
        this.maze = new MazeProcess(
            maze,
            new MazeProcess.MazeSettings(settingsProcess.start(), settingsProcess.end())
        );
    }

    public void createMaze() throws NotInitializedException {
        new MazeGenerationController(new MazeGenerationProcess(maze)).execute();
    }

    public void solveMaze() throws NotInitializedException {
        new MazeSolvingController(new MazeSolvingProcess(maze)).execute();
    }
}
