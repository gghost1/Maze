package backend.academy.controller;

import backend.academy.exception.NotInitializedException;
import backend.academy.exception.UnsuccessfulPreviousProcess;
import backend.academy.game.process.CoreProcess;
import backend.academy.game.process.SettingsProcess;
import java.lang.reflect.InvocationTargetException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j public class CoreController extends Executable {

    @Getter
    private final CoreProcess coreProcess;

    public CoreController(SettingsProcess settingsProcess)
        throws NotInitializedException,
        InvocationTargetException,
        NoSuchMethodException,
        InstantiationException,
        IllegalAccessException,
        UnsuccessfulPreviousProcess {
        coreProcess = new CoreProcess(settingsProcess);
    }

    @Override
    public void execute() {
        boolean next = false;
        while (!next) {
            output.writeOutput(dictionary.getString("To generation the maze input 1"));
            if (listener.readInputLine().contains("1")) {
                try {
                    coreProcess.createMaze();
                } catch (NotInitializedException e) {
                    output.writeOutput(dictionary.exceptionSomethingWentWrong());
                    log.error(e.getMessage());
                    throw new RuntimeException();
                }
                next = true;
            }
        }
        next = false;
        while (!next) {
            output.writeOutput(dictionary.getString("To solve the maze input 1"));
            if (listener.readInputLine().contains("1")) {
                try {
                    coreProcess.solveMaze();
                } catch (NotInitializedException e) {
                    output.writeOutput(dictionary.exceptionSomethingWentWrong());
                    log.error(e.getMessage());
                    throw new RuntimeException();
                }
                next = true;
            }
        }
    }
}
