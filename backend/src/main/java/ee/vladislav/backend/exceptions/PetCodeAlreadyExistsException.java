package ee.vladislav.backend.exceptions;

import org.springframework.http.HttpStatus;

public class PetCodeAlreadyExistsException extends RuntimeException{
    private final String message = "Duplicate pet code";
    private final HttpStatus status = HttpStatus.CONFLICT;

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }}
