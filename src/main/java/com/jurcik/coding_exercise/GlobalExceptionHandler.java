package com.jurcik.coding_exercise;

import com.jurcik.coding_exercise.util.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> handleCustomError(ApiException ex) {
       return new ResponseEntity<>(ex.getMessage(), ex.getHttpStatus());
    }
}
