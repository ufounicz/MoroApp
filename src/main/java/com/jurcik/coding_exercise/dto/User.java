package com.jurcik.coding_exercise.dto;

import com.jurcik.coding_exercise.util.UserRole;

import java.util.UUID;

public record User(UUID userId, String name, String username, UserRole role) {}
