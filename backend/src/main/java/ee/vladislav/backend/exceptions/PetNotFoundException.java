package ee.vladislav.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

public class PetNotFoundException extends RuntimeException {
    private final String message = "Pet with the provided id not found";
    private final HttpStatus status = HttpStatus.NOT_FOUND;

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}