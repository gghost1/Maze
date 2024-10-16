package backend.academy.io;

import backend.academy.exception.NotInitializedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public final class CustomInput {

    private volatile static CustomInput instance;
    private final BufferedReader reader;

    public static synchronized CustomInput getInstance(Reader reader) {
        if (instance == null) {
            instance = new CustomInput(reader);
        }
        return instance;
    }

    public static synchronized CustomInput getInstance() throws NotInitializedException {
        if (instance == null) {
            throw new NotInitializedException("Input not initialized");
        }
        return instance;
    }

    public static void reset() {
        instance = null;
    }

    private CustomInput(Reader reader) {
        this.reader = new BufferedReader(reader);
    }

    public String readInputLine() {
        try {
            String input = reader.readLine();
            while (input == null) {
                input = reader.readLine();
            }
            return input;
        } catch (IOException ex) {
            return "";
        }
    }
}
