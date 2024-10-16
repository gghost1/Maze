package backend.academy.io.output;

import java.util.List;

public interface CustomOutputWrapper {
    void writeOutput(String output);

    void writeOutput(List<String> output, boolean newLine, String splitter);

}
