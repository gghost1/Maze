package backend.academy.algorithm.controllerTest;

import backend.academy.controller.CoreController;
import backend.academy.controller.SettingsController;
import backend.academy.exception.NotInitializedException;
import backend.academy.io.CustomInput;
import backend.academy.io.CustomOutput;
import backend.academy.io.language.LanguageManager;
import org.junit.jupiter.api.Test;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;

public class CoreControllerTest {

    @Test
    public void executeTest()
        throws NotInitializedException, InvocationTargetException, NoSuchMethodException, InstantiationException,
        IllegalAccessException {
        CustomOutput.getInstance(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
        CustomInput.reset();
        CustomInput.getInstance(new StringReader("5\n5\n0 0\n4 4\n0\n0\n1\n1"));
        LanguageManager.getDictionary("en");

        SettingsController settingsController = new SettingsController();
        settingsController.execute();

        CoreController coreController = new CoreController(settingsController.settingsProcess());

        coreController.execute();
    }
}
