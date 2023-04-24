package ee.vladislav.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR,  reason = "Pet not added")
public class PetNotAddedException extends RuntimeException{
}
