package backend.academy.controller;

import backend.academy.io.CustomInput;
import backend.academy.io.language.Dictionary;
import backend.academy.io.output.CustomOutputWrapper;

public abstract class Executable {
    protected final CustomInput listener;
    protected final CustomOutputWrapper output;
    protected final Dictionary dictionary;

    protected Executable(CustomInput listener, CustomOutputWrapper output, Dictionary dictionary) {
        this.listener = listener;
        this.output = output;
        this.dictionary = dictionary;
    }

    public abstract void execute();
}
