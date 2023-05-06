package ee.vladislav.backend.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

public class PetNotUpdatedException extends RuntimeException {
    private final HttpStatus status = HttpStatus.NOT_FOUND;

    public PetNotUpdatedException(String msg) {
        super(msg);
    }

    public PetNotUpdatedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public HttpStatus getStatus() {
        return status;
    }
}