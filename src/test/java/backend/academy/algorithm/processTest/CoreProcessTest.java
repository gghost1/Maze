package backend.academy.algorithm.processTest;

import backend.academy.exception.UnsuccessfulPreviousProcessException;
import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.algorithm.findPath.FindMazePathAlgorithm;
import backend.academy.game.maze.algorithm.generate.CreateMazeAlgorithm;
import backend.academy.game.process.CoreProcess;
import backend.academy.game.process.SettingsProcess;
import backend.academy.io.CustomInput;
import backend.academy.io.language.Language;
import backend.academy.io.language.LanguageManager;
import backend.academy.io.output.CustomOutput;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CoreProcessTest {

    @BeforeEach
    public void setUp() {
        LanguageManager.getDictionary(Language.en);
        CustomInput.getInstance(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        CustomOutput.getInstance(new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8)));
    }

    @Test
    public void createCoreProcessTest() {
        SettingsProcess settingsProcess = getSettingsProcess();
        assertDoesNotThrow(() -> CoreProcess.create(settingsProcess));
    }

    @Test
    public void createCoreProcessNegativeTest() {
        SettingsProcess settingsProcess = getSettingsProcess();
        settingsProcess.mazeWidth(0);
        settingsProcess.mazeHeight(0);
        assertThrows(UnsuccessfulPreviousProcessException.class, () -> CoreProcess.create(settingsProcess));
    }

    @Test
    public void generateMazeTest() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        SettingsProcess settingsProcess = getSettingsProcess();
        CoreProcess coreProcess = CoreProcess.create(settingsProcess);
        assertDoesNotThrow(coreProcess::createMaze);
    }

    @Test
    public void solveMazeTest() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        SettingsProcess settingsProcess = getSettingsProcess();
        CoreProcess coreProcess = CoreProcess.create(settingsProcess);
        assertDoesNotThrow(coreProcess::solveMaze);
    }

    private SettingsProcess getSettingsProcess() {
        SettingsProcess settingsProcess = new SettingsProcess();
        settingsProcess.mazeWidth(5);
        settingsProcess.mazeHeight(5);
        settingsProcess.start(new Point(0, 0));
        settingsProcess.end(new Point(4, 4));
        settingsProcess.createMazeAlgorithm(CreateMazeAlgorithm.PRIMS);
        settingsProcess.findMazePathAlgorithm(FindMazePathAlgorithm.DEAD_END_FILLER);
        return settingsProcess;
    }

}
