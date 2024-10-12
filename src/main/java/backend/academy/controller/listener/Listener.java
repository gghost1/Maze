package backend.academy.controller.listener;

import backend.academy.exception.NotInitializedException;
import backend.academy.io.CustomInput;
import java.io.Reader;
import java.util.LinkedList;
import java.util.Queue;

public class Listener {
    private static Listener instance;
    private final CustomInput input;

    private final Queue<String> inputQueue;

    private Listener(CustomInput input) {
        this.input = input;
        this.inputQueue = new LinkedList<>();
        Thread thread = new Thread(this::parseInput);
        thread.start();
    }

    public static Listener getInstance(Reader reader) {
        if (instance == null) {
            instance = new Listener(CustomInput.getInstance(reader));
        }
        return instance;
    }

    public static Listener getInstance() throws NotInitializedException {
        if (instance == null) {
            throw new NotInitializedException("Listener not initialized");
        }
        return instance;
    }

    private synchronized void parseInput() {
        while (true) {
            inputQueue.add(input.readInput());
        }
    }

    public synchronized String getFirstInput() {
        while (true) {
            if (!inputQueue.isEmpty()) {
                break;
            }
        }
        return inputQueue.poll();
    }




}
