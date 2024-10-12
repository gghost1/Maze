package backend.academy.io;

import backend.academy.exception.NotInitializedException;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.function.Function;

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

    public void writeOutput(String output, boolean newLine, String splitter) {
        writer.print(output + (newLine ? "\n" : splitter));
    }

    public void writeOutput(String output) {
        writer.print(output + "\n");
        flush();
    }

    protected void writeOutput(List<String> output) {
        output.forEach(x -> writer.print(x + "\n"));
    }

    protected void writeOutput(List<String> output, boolean newLine, String splitter) {
        output.forEach(x -> writer.print(x + (newLine ? "\n" : splitter)));
    }

    protected void writeOutput(List<String> output, Function<String, String> function) {
        output.forEach(x -> writer.print(function.apply(x) + "\n"));
    }

    protected void writeOutput(List<String> output,
        Function<String, String> function,
        boolean newLine, String splitter) {
        output.forEach(x -> writer.print(function.apply(x) + (newLine ? "\n" : splitter)));
    }

    protected void flush() {
        writer.flush();
    }

    protected void clear() {
        AnsiConsole.systemInstall();
        writer.print(Ansi.ansi().eraseScreen());
        flush();
    }

    public void exception(String message) {
        writeOutput(message);
        flush();
    }

}
