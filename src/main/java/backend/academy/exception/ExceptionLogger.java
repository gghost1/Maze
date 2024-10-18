package backend.academy.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionLogger {

    private ExceptionLogger() {}

    public static void log(Exception e) {
        log.error("Error occurred {}", e.getMessage());
    }
}
