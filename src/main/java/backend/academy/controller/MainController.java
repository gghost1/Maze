package backend.academy.controller;

import backend.academy.exception.NotInitializedException;
import backend.academy.exception.UnsuccessfulPreviousProcess;
import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.InvocationTargetException;

@Slf4j public class MainController extends Executable {

    public MainController() throws NotInitializedException {
    }

    @Override
    public void execute() {
        output.writeOutput(dictionary.getString("Hi! You are in the maze program. Let's make a settings"));

        try {
            boolean done = false;
            while (!done) {
                SettingsController settingsController = new SettingsController();
                settingsController.execute();
                done = true;
                try {
                    CoreController coreController = new CoreController(settingsController.settingsProcess());
                    coreController.execute();
                } catch (UnsuccessfulPreviousProcess e) {
                    output.writeOutput(e.previousException().getMessage());
                    output.writeOutput("Make settings again");
                    done = false;
                }
            }
        } catch (NotInitializedException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            output.writeOutput(dictionary.getString("Something went wrong"));
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
