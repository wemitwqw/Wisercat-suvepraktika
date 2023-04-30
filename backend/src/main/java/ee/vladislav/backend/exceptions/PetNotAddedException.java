package ee.vladislav.backend.exceptions;

import org.springframework.http.HttpStatus;

public class PetNotAddedException extends RuntimeException {
    private final String message = "Pet not added!";
    private final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
