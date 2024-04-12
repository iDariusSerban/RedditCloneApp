package RedditClone.config;


import RedditClone.exceptions.ResourceNotFoundException;
import RedditClone.exceptions.ResourceNotValidException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(ResourceNotValidException.class)
    public ResponseEntity<Object> handleNotValidException(ResourceNotValidException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);

    }

}
