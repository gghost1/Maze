package backend.academy.controller;

import backend.academy.exception.ExceptionLogger;
import backend.academy.exception.NotInitializedException;
import backend.academy.exception.UnsuccessfulPreviousProcessException;
import backend.academy.io.CustomInput;
import backend.academy.io.language.LanguageManager;
import backend.academy.io.output.CustomOutput;
import java.lang.reflect.InvocationTargetException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainController extends Executable {

    public MainController() throws NotInitializedException {
        super(CustomInput.getInstance(), CustomOutput.getInstance(), LanguageManager.dictionary());
    }

    @Override
    public void execute() {
        output.writeOutput(dictionary.getString("Hi! You are in the maze program. Let's make a settings"));
        try {
            boolean done;
            SettingsController settingsController = new SettingsController();
            do {
                settingsController.execute();
                done = true;
                try {
                    CoreController coreController = CoreController.create(settingsController.settingsProcess());
                    coreController.execute();
                } catch (UnsuccessfulPreviousProcessException e) {
                    output.writeOutput(e.previousException().getMessage());
                    output.writeOutput("Make settings again");
                    done = false;
                }
            } while (!done);
        } catch (NotInitializedException
                 | InvocationTargetException
                 | NoSuchMethodException
                 | InstantiationException
                 | IllegalAccessException e) {
            output.writeOutput(dictionary.getString("Something went wrong"));
            ExceptionLogger.log(e);
            throw new RuntimeException(e);
        }

    }
}
