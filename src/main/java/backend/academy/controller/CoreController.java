package backend.academy.controller;

import backend.academy.exception.NotInitializedException;
import backend.academy.game.process.CoreProcess;
import backend.academy.game.process.SettingsProcess;
import lombok.Getter;
import java.lang.reflect.InvocationTargetException;

public class CoreController extends Executable {

    @Getter
    private final CoreProcess coreProcess;

    public CoreController(SettingsProcess settingsProcess)
        throws NotInitializedException, InvocationTargetException, NoSuchMethodException, InstantiationException,
        IllegalAccessException {
        coreProcess = new CoreProcess(settingsProcess);
    }

    @Override
    public void execute() {
        // TODO add output in CoreController
    }
}
