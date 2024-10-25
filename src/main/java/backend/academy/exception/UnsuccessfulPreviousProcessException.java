package backend.academy.exception;

import lombok.Getter;

@Getter
public class UnsuccessfulPreviousProcessException extends RuntimeException {

    private final Exception previousException;

    public UnsuccessfulPreviousProcessException(String message, Exception e) {
        super(message);
        this.previousException = e;
    }
}
