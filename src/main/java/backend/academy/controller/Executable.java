package backend.academy.controller;

import backend.academy.controller.listener.Listener;
import backend.academy.exception.NotInitializedException;
import backend.academy.io.CustomOutput;

public abstract class Executable {
    protected final Listener listener;
    protected final CustomOutput output;

    protected Executable() throws NotInitializedException {
        listener = Listener.getInstance();
        output = CustomOutput.getInstance();
    }

    public abstract void execute();
}
