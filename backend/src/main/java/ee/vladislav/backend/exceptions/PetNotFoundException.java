package ee.vladislav.backend.exceptions;

import org.springframework.http.HttpStatus;

public class PetNotFoundException extends RuntimeException {
    private final String message = "Pet with the provided id either does not exist or You don't have permission to view and modify it!";
    private final HttpStatus status = HttpStatus.NOT_FOUND;

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}