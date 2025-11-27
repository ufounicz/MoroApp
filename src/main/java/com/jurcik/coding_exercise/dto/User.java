package com.jurcik.coding_exercise.dto;

import java.util.UUID;

public record User(UUID userId, String name, String username) {}
