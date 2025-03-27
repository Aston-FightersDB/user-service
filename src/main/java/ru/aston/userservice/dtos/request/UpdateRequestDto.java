package ru.aston.userservice.dtos.request;

import static ru.aston.userservice.validation.ValidationRegex.REGEXP_PASSWORD;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateRequestDto(
    @NotNull
    String userId,
    @NotNull
    String username,
    @NotNull
    @Size(min = 8, max = 50)
    @Pattern(regexp = REGEXP_PASSWORD)
    String password
) {

}
