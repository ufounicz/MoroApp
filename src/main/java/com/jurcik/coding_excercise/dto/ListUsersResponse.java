package com.jurcik.coding_excercise.dto;

import java.util.Map;
import java.util.UUID;

public record ListUsersResponse(Map<UUID, String> users) {
}
