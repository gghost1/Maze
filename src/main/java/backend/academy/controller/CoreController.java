package backend.academy.controller;

import backend.academy.exception.ExceptionLogger;
import backend.academy.exception.NotInitializedException;
import backend.academy.game.process.CoreProcess;
import backend.academy.game.process.SettingsProcess;
import backend.academy.io.CustomInput;
import backend.academy.io.language.Dictionary;
import backend.academy.io.language.LanguageManager;
import backend.academy.io.output.CustomOutput;
import backend.academy.io.output.CustomOutputWrapper;
import java.lang.reflect.InvocationTargetException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CoreController extends Executable {

    @Getter
    private final CoreProcess coreProcess;

    public static CoreController create(SettingsProcess settingsProcess)
        throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException,
        NotInitializedException {
        return new CoreController(
            CoreProcess.create(settingsProcess),
            CustomInput.getInstance(),
            CustomOutput.getInstance(),
            LanguageManager.dictionary()
        );
    }

    private CoreController(
        CoreProcess coreProcess,
        CustomInput customInput,
        CustomOutputWrapper customOutputWrapper,
        Dictionary dictionary) {
        super(customInput, customOutputWrapper, dictionary);
        this.coreProcess = coreProcess;
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
                    ExceptionLogger.log(e);
                    throw new RuntimeException(e);
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
                    ExceptionLogger.log(e);
                    throw new RuntimeException(e);
                }
                next = true;
            }
        }
    }
}
