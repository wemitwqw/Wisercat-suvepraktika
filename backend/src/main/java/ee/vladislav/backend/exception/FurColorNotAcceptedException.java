package ee.vladislav.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Fur color not acceptable")
public class FurColorNotAcceptedException extends NoSuchElementException {
}
