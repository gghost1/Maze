package backend.academy.io.output;

import backend.academy.exception.NotInitializedException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

public final class CustomOutput implements CustomOutputWrapper {
    private static volatile CustomOutputWrapper instance;
    private final PrintWriter writer;

    public static synchronized CustomOutputWrapper getInstance(Writer writer) {
        if (instance == null) {
            instance = new CustomOutput(writer);
        }
        return new CustomOutputWrapperImpl(instance);
    }

    public static synchronized CustomOutputWrapper getInstance() throws NotInitializedException {
        if (instance == null) {
            throw new NotInitializedException("Output not initialized");
        }
        return new CustomOutputWrapperImpl(instance);
    }

    private CustomOutput(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    @Override
    public void writeOutput(String output) {
        synchronized (this) {
            writer.print(output + "\n");
            flush();
        }
    }

    @Override
    public void writeOutput(List<String> output, boolean newLine, String splitter) {
        synchronized (this) {
            output.forEach(x -> writer.print(x + (newLine ? "\n" : splitter)));
            flush();
        }
    }

    private void flush() {
        writer.flush();
    }

    static class CustomOutputWrapperImpl implements CustomOutputWrapper {
        private final CustomOutputWrapper output;

        CustomOutputWrapperImpl(CustomOutputWrapper output) {
            this.output = output;
        }

        @Override
        public void writeOutput(String output) {
            this.output.writeOutput(output);
        }

        @Override
        public void writeOutput(List<String> output, boolean newLine, String splitter) {
            this.output.writeOutput(output, newLine, splitter);
        }
    }
}
