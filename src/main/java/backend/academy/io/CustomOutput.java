package backend.academy.io;

import backend.academy.exception.NotInitializedException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

public class CustomOutput {
    private static CustomOutput instance;
    private final PrintWriter writer;

    public static CustomOutput getInstance(Writer writer) {
        if (instance == null) {
            instance = new CustomOutput(writer);
        }
        return instance;
    }

    public static CustomOutput getInstance() throws NotInitializedException {
        if (instance == null) {
            throw new NotInitializedException("Output not initialized");
        }
        return instance;
    }

    private CustomOutput(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public void writeOutput(String output) {
        writer.print(output + "\n");
        flush();
    }

    public void writeOutput(List<String> output, boolean newLine, String splitter) {
        output.forEach(x -> writer.print(x + (newLine ? "\n" : splitter)));
        flush();
    }

    private void flush() {
        writer.flush();
    }
}
