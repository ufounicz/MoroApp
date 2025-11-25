package com.jurcik.coding_excercise.util;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class UserNotFoundException extends ApiException {
    public UserNotFoundException(UUID id) {
        super("User not found: " + id, HttpStatus.NOT_FOUND);
    }
}
