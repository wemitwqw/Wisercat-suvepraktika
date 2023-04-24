package ee.vladislav.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Pet with that id not found")
public class PetNotFoundException extends NoSuchElementException {

//    public CustomNotFoundException(String message) {
//        super(message);
//    }

}