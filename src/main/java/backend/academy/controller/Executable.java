package backend.academy.controller;

import backend.academy.exception.NotInitializedException;
import backend.academy.io.CustomInput;
import backend.academy.io.CustomOutput;
import backend.academy.io.language.Dictionary;
import backend.academy.io.language.LanguageManager;

public abstract class Executable {
    protected final CustomInput listener;
    protected final CustomOutput output;
    protected final Dictionary dictionary;

    protected Executable() throws NotInitializedException {
        dictionary = LanguageManager.dictionary();
        listener = CustomInput.getInstance();
        output = CustomOutput.getInstance();
    }

    public abstract void execute();
}
