package com.jurcik.coding_exercise.util;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ApiException {
    public UserNotFoundException(String identifier) {
        super("User not found: " + identifier, HttpStatus.NOT_FOUND);
    }
}
