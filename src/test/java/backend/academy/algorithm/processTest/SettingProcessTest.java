package backend.academy.algorithm.processTest;

import backend.academy.exception.IllegalSettingParameter;
import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.algorithm.findPath.FindMazePathAlgorithm;
import backend.academy.game.maze.algorithm.generate.CreateMazeAlgorithm;
import backend.academy.game.process.SettingsProcess;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SettingProcessTest {

    @Test
    public void sizeValidationTest() {
        SettingsProcess settingsProcess = getSettingsProcess();
        settingsProcess.mazeWidth(0);
        assertThrows(IllegalSettingParameter.class, settingsProcess::isValid);

        settingsProcess = getSettingsProcess();
        settingsProcess.mazeHeight(0);
        assertThrows(IllegalSettingParameter.class, settingsProcess::isValid);
    }

    @ParameterizedTest
    @MethodSource("provideIllegalPoints")
    public void startPointValidationTest(Point point) {
        SettingsProcess settingsProcess = getSettingsProcess();
        settingsProcess.start(point);
        assertThrows(IllegalSettingParameter.class, settingsProcess::isValid);
    }

    @ParameterizedTest
    @MethodSource("provideIllegalPoints")
    public void endPointValidationTest(Point point) {
        SettingsProcess settingsProcess = getSettingsProcess();
        settingsProcess.end(point);
        assertThrows(IllegalSettingParameter.class, settingsProcess::isValid);
    }

    private static Stream<Point> provideIllegalPoints() {
        return Stream.of(
            new Point(-1, 0),
            new Point(0, -1),
            new Point(5, 0),
            new Point(0, 5),
            new Point(5, 5)
        );
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
