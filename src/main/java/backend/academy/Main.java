package backend.academy;

import backend.academy.controller.MainController;
import backend.academy.exception.NotInitializedException;
import backend.academy.io.CustomInput;
import backend.academy.io.CustomOutput;
import backend.academy.io.language.Language;
import backend.academy.io.language.LanguageManager;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Slf4j @UtilityClass
public class Main {
    public static void main(String[] args) {
        CustomInput.getInstance(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        CustomOutput.getInstance(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
        LanguageManager.getDictionary(Language.en.name());
        try {
            MainController mainController = new MainController();
            mainController.execute();
        } catch (NotInitializedException e) {
            log.error(e.getMessage());
            throw new RuntimeException();
        }
    }
}
