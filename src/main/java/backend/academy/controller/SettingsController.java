package backend.academy.controller;

import backend.academy.exception.NotInitializedException;
import backend.academy.game.process.SettingsProcess;
import lombok.Getter;

public class SettingsController extends Executable {
    @Getter
    private final SettingsProcess settingsProcess;

    public SettingsController() throws NotInitializedException {
        settingsProcess = new SettingsProcess();

    }

    @Override
    void execute() {
        output.writeOutput("");
        settingsProcess.mazeWidth(Integer.parseInt(listener.getFirstInput()));
    }
}
