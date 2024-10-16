package backend.academy;

import backend.academy.controller.MainController;
import backend.academy.exception.NotInitializedException;
import backend.academy.io.CustomInput;
import backend.academy.io.language.Language;
import backend.academy.io.language.LanguageManager;
import backend.academy.io.output.CustomOutput;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j @UtilityClass
public class Main {
    public static void main(String[] args) {
        CustomInput.getInstance(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        CustomOutput.getInstance(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
        LanguageManager.getDictionary(Language.en);
        try {
            MainController mainController = new MainController();
            mainController.execute();
        } catch (NotInitializedException e) {
            log.error("Error occurred {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
