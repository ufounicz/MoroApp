package com.jurcik.coding_exercise.dto;

import com.jurcik.coding_exercise.util.UserRole;

import java.util.UUID;

public record CreateUserResponse(UUID id, String name, String username, UserRole role) {
}
