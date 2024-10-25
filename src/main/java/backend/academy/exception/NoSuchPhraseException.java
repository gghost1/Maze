package backend.academy.exception;

public class NoSuchPhraseException extends RuntimeException {
    public NoSuchPhraseException(String message) {
        super(message);
    }
}
