package com.jurcik.coding_exercise.dto;

import com.jurcik.coding_exercise.util.UserRole;
import org.hibernate.validator.constraints.Length;

public record ModifyUserRequest(
        @Length(max = 255, min = 1) String name,
        @Length(max = 255, min = 5) String username,
        @Length(max = 255, min = 7) String password,
        UserRole role
) {
}
