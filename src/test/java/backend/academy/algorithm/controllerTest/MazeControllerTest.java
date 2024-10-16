package backend.academy.algorithm.controllerTest;

import backend.academy.controller.MazeGenerationController;
import backend.academy.controller.MazeSolvingController;
import backend.academy.exception.NotInitializedException;
import backend.academy.game.maze.Maze;
import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.algorithm.findPath.DeadEndFiller;
import backend.academy.game.maze.algorithm.findPath.ShortestPathFinder;
import backend.academy.game.maze.algorithm.generate.PrimsAlgorithm;
import backend.academy.game.process.maze.MazeGenerationProcess;
import backend.academy.game.process.maze.MazeProcess;
import backend.academy.game.process.maze.MazeSolvingProcess;
import backend.academy.io.CustomInput;
import backend.academy.io.output.CustomOutput;
import backend.academy.io.language.Language;
import backend.academy.io.language.LanguageManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.OutputStreamWriter;
import java.io.StringReader;

public class MazeControllerTest {

    @BeforeEach
    public void setUp() {
        LanguageManager.getDictionary(Language.en);
        CustomInput.reset();
        CustomInput.getInstance(new StringReader(""));
        CustomOutput.getInstance(new OutputStreamWriter(System.out));
    }

    @Test
    public void executeGenerationTest() throws NotInitializedException {
        MazeGenerationController mazeGenerationController =
            new MazeGenerationController(
                new MazeGenerationProcess(
                    new MazeProcess(
                        new Maze(5, 5, new PrimsAlgorithm(), new ShortestPathFinder()),
                        new MazeProcess.MazeSettings(new Point(0,0), new Point(4,4)))));
        mazeGenerationController.execute();
    }

    @Test
    public void executeSolvingTest() throws NotInitializedException {
        Maze maze = new Maze(5, 5, new PrimsAlgorithm(), new DeadEndFiller());
        maze.generateMaze(new Point(0,0), new Point(4,4));
        MazeSolvingController mazeSolvingController =
            new MazeSolvingController(
                new MazeSolvingProcess(
                    new MazeProcess(maze, new MazeProcess.MazeSettings(new Point(0,0), new Point(4,4)))));
        mazeSolvingController.execute();
    }

}
