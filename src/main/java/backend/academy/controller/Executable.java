package backend.academy.controller;

import backend.academy.controller.listener.Listener;
import backend.academy.exception.NotInitializedException;
import backend.academy.io.CustomOutput;
import backend.academy.io.language.Dictionary;
import backend.academy.io.language.LanguageManager;

public abstract class Executable {
    protected final Listener listener;
    protected final CustomOutput output;
    protected final Dictionary dictionary;

    protected Executable() throws NotInitializedException {
        dictionary = LanguageManager.dictionary();
        listener = Listener.getInstance();
        output = CustomOutput.getInstance();
    }

    public abstract void execute();
}
