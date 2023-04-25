package ee.vladislav.backend.exceptions;

import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;

public class FurColorNotAcceptedException extends NoSuchElementException {
    private final String message = "Fur color type not accepted";
    private final HttpStatus status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
