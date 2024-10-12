package backend.academy.game.process;

import backend.academy.exception.PathNotFoundException;
import backend.academy.game.maze.Maze;
import java.lang.reflect.InvocationTargetException;

public class CoreProcess {

    private final SettingsProcess settingsProcess;
    private Maze maze;

    public CoreProcess(SettingsProcess settingsProcess)
        throws NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException
    {
        this.settingsProcess = settingsProcess;
        this.maze = new Maze(
            settingsProcess.mazeWidth(),
            settingsProcess.mazeHeight(),
            settingsProcess.createMazeAlgorithm().getAlgorithm().getConstructor().newInstance(),
            settingsProcess.findMazePathAlgorithm().getAlgorithm().getConstructor().newInstance()
        );
    }

    public void createMaze() {
        maze.generateMaze(settingsProcess.start(), settingsProcess.end());
    }

    public void solveMaze() throws PathNotFoundException {
        maze.solveMaze(settingsProcess.start(), settingsProcess.end());
        // TODO add output in MazeController
    }

}
