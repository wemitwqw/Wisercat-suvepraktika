package ee.vladislav.backend.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

public class PetNotUpdatedException extends DataIntegrityViolationException {
//    private final String message = "Pet with the provided id either does not exist or You don't have permission to view and modify it!";
    private final HttpStatus status = HttpStatus.NOT_FOUND;

    public PetNotUpdatedException(String msg) {
        super(msg);
    }

    public PetNotUpdatedException(String msg, Throwable cause) {
        super(msg, cause);
    }

//    @Override
//    public String getMessage() {
//        return message;
//    }

    public HttpStatus getStatus() {
        return status;
    }
}