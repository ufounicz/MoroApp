package com.jurcik.coding_exercise.dto;

import com.jurcik.coding_exercise.util.UserRole;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CreateUserRequest(
        @NotNull @Length(max = 255, min = 1) String name,
        @NotNull @Length(max = 255, min = 5) String username,
        @NotNull @Length(max = 255, min = 7) String password,
        @NotNull UserRole role
) {}
