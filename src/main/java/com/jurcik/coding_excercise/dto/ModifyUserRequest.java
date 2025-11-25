package com.jurcik.coding_excercise.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record ModifyUserRequest(
        @NotNull @Length(max = 255, min = 1) String name
) {
}
