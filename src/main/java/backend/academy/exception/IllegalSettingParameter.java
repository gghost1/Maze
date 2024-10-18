package backend.academy.exception;

public class IllegalSettingParameter extends RuntimeException {
    public IllegalSettingParameter(String message) {
        super(message);
    }
}

