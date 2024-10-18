package backend.academy.algorithm.controllerTest;

import backend.academy.controller.SettingsController;
import backend.academy.exception.NotInitializedException;
import backend.academy.game.maze.algorithm.findPath.FindMazePathAlgorithm;
import backend.academy.game.maze.algorithm.generate.CreateMazeAlgorithm;
import backend.academy.io.CustomInput;
import backend.academy.io.language.Language;
import backend.academy.io.language.LanguageManager;
import backend.academy.io.output.CustomOutput;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SettingsControllerTest {

    @Test
    public void executeAverageTest() throws NotInitializedException {
        CustomOutput.getInstance(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
        CustomInput.reset();
        CustomInput.getInstance(new StringReader("5\n5\n0 0\n4 4\n0\n0"));
        LanguageManager.getDictionary(Language.en);

        SettingsController settingsController = new SettingsController();
        settingsController.execute();

        assertEquals(5, settingsController.settingsProcess().mazeWidth());
        assertEquals(5, settingsController.settingsProcess().mazeHeight());
        assertEquals(0, settingsController.settingsProcess().start().x());
        assertEquals(0, settingsController.settingsProcess().start().y());
        assertEquals(4, settingsController.settingsProcess().end().x());
        assertEquals(4, settingsController.settingsProcess().end().y());
        assertTrue(Arrays.stream(CreateMazeAlgorithm.values()).toList().contains(settingsController.settingsProcess().createMazeAlgorithm()));
        assertTrue(Arrays.stream(FindMazePathAlgorithm.values()).toList().contains(settingsController.settingsProcess().findMazePathAlgorithm()));
    }

    @Test
    public void executeTwoPathsTest() throws NotInitializedException {
        CustomOutput.getInstance(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
        CustomInput.reset();
        CustomInput.getInstance(new StringReader("5\n5\n0 0\n4 4\n3"));
        LanguageManager.getDictionary(Language.en);

        SettingsController settingsController = new SettingsController();
        settingsController.execute();

        assertEquals(5, settingsController.settingsProcess().mazeWidth());
        assertEquals(5, settingsController.settingsProcess().mazeHeight());
        assertEquals(0, settingsController.settingsProcess().start().x());
        assertEquals(0, settingsController.settingsProcess().start().y());
        assertEquals(4, settingsController.settingsProcess().end().x());
        assertEquals(4, settingsController.settingsProcess().end().y());
        assertEquals(CreateMazeAlgorithm.WAVE_PROPAGATION, settingsController.settingsProcess().createMazeAlgorithm());
        assertEquals(FindMazePathAlgorithm.A_STAR, settingsController.settingsProcess().findMazePathAlgorithm());
    }

    @Test
    public void executeWrongInputTest() throws NotInitializedException {
        CustomOutput.getInstance(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
        CustomInput.reset();
        CustomInput.getInstance(new StringReader("a\n5\na\n5\na a\n0 0\n4 a\n4 4\npr\n1\n-1\na\ndead end filler"));
        LanguageManager.getDictionary(Language.en);

        SettingsController settingsController = new SettingsController();
        settingsController.execute();

        assertEquals(5, settingsController.settingsProcess().mazeWidth());
        assertEquals(5, settingsController.settingsProcess().mazeHeight());
        assertEquals(0, settingsController.settingsProcess().start().x());
        assertEquals(0, settingsController.settingsProcess().start().y());
        assertEquals(4, settingsController.settingsProcess().end().x());
        assertEquals(4, settingsController.settingsProcess().end().y());
        assertTrue(Arrays.stream(CreateMazeAlgorithm.values()).toList().contains(settingsController.settingsProcess().createMazeAlgorithm()));
        assertEquals(FindMazePathAlgorithm.DEAD_END_FILLER, settingsController.settingsProcess().findMazePathAlgorithm());
    }

}
