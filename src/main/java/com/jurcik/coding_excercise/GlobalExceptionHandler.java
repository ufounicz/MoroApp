package com.jurcik.coding_excercise;

import com.jurcik.coding_excercise.util.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.Serializable;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Serializable> handleCustomError(ApiException ex) {
       return new ResponseEntity<Serializable>(ex.getMessage(), ex.getHttpStatus());
    }
}
