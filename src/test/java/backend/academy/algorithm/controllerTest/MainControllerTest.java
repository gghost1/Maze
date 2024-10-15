package backend.academy.algorithm.controllerTest;

import backend.academy.controller.MainController;
import backend.academy.exception.NotInitializedException;
import backend.academy.io.CustomInput;
import backend.academy.io.CustomOutput;
import backend.academy.io.language.LanguageManager;
import org.junit.jupiter.api.Test;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

public class MainControllerTest {

    @Test
    public void executeTest() throws NotInitializedException {
        CustomOutput.getInstance(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
        CustomInput.reset();
        CustomInput.getInstance(new StringReader("5\n5\n0 0\n4 4\n0\n0\n1\n1"));
        LanguageManager.getDictionary("en");
        MainController mainController = new MainController();
        mainController.execute();
    }

    @Test
    public void executeWrongSettingsTest() throws NotInitializedException {
        CustomOutput.getInstance(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
        CustomInput.reset();
        CustomInput.getInstance(new StringReader("5\n5\n0 0\n6 4\n0\n0\n5\n5\n0 0\n5 5\n0\n0\n1\n1"));
        LanguageManager.getDictionary("en");
        MainController mainController = new MainController();
        mainController.execute();
    }
}
