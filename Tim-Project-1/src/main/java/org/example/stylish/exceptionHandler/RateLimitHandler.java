package org.example.stylish.exceptionHandler;

import org.example.stylish.exception.RateLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RateLimitHandler {
    @ExceptionHandler(RateLimitExceededException.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public ResponseEntity<?> handleRateLimitExceededException(RateLimitExceededException e) {
        return new ResponseEntity<>("Rate limit reached", HttpStatus.TOO_MANY_REQUESTS);
    }
}
