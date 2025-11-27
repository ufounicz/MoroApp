package com.jurcik.coding_exercise.dto;

import java.util.UUID;

public record CreateUserResponse(UUID id, String name, String username) {
}
