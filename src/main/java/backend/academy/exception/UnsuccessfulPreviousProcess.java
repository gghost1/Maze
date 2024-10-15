package backend.academy.exception;

public class UnsuccessfulPreviousProcess extends RuntimeException {

    private final Exception previousException;

    public UnsuccessfulPreviousProcess(String message, Exception e) {
        super(message);
        this.previousException = e;
    }
}