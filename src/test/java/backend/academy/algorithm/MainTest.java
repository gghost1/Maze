package backend.academy.algorithm;

import backend.academy.controller.MainController;
import backend.academy.exception.NotInitializedException;
import backend.academy.io.CustomInput;
import backend.academy.io.output.CustomOutput;
import backend.academy.io.language.Language;
import backend.academy.io.language.LanguageManager;
import org.junit.jupiter.api.Test;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

public class MainTest {

    @Test
    public void mainEngTest() {
        CustomInput.reset();
        CustomInput.getInstance(new StringReader("5\n5\n0 0\n4 4\n0\n0\n1\n1"));
        CustomOutput.getInstance(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
        LanguageManager.getDictionary(Language.en);
        try {
            MainController mainController = new MainController();
            mainController.execute();
        } catch (NotInitializedException e) {
            throw new RuntimeException();
        }
    }

    @Test
    public void mainRuTest() {
        CustomInput.reset();
        CustomInput.getInstance(new StringReader("5\n5\n0 0\n4 4\n0\n0\n1\n1"));
        CustomOutput.getInstance(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
        LanguageManager.getDictionary(Language.ru);
        try {
            MainController mainController = new MainController();
            mainController.execute();
        } catch (NotInitializedException e) {
            throw new RuntimeException();
        }
    }
}
