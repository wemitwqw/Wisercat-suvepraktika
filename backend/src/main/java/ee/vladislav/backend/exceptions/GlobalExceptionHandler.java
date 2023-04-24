package ee.vladislav.backend.exceptions;

import ee.vladislav.backend.model.ExceptionResponse;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PetNotFoundException.class)
    public ResponseEntity<Object> handlePetNotFound( PetNotFoundException ex, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Pet with the provided id not found");
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AnimalTypeNotAcceptedException.class)
    public ResponseEntity<Object> handleInvalidAnimalType( AnimalTypeNotAcceptedException ex, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Animal type not accepted");
        return new ResponseEntity<>(response,HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(CountryNotAcceptedException.class)
    public ResponseEntity<Object> handleInvalidCountry( CountryNotAcceptedException ex, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Country type not accepted");
        return new ResponseEntity<>(response,HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(FurColorNotAcceptedException.class)
    public ResponseEntity<Object> handleInvalidFurColor( FurColorNotAcceptedException ex, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Fur color type not accepted");
        return new ResponseEntity<>(response,HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(PetCodeAlreadyExistsException.class)
    public ResponseEntity<Object> handlePetCodeAlreadyExists( PetCodeAlreadyExistsException ex, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Duplicate pet code");
        return new ResponseEntity<>(response,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PetNotAddedException.class)
    public ResponseEntity<Object> handlePetNotAdded( PetNotAddedException ex, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Pet not added");
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}