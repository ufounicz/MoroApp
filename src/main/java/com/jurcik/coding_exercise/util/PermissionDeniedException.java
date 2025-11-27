package com.jurcik.coding_exercise.util;

import org.springframework.http.HttpStatus;

public class PermissionDeniedException extends ApiException {
    public PermissionDeniedException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
