package ru.aston.userservice.dtos.request;

import static ru.aston.userservice.validation.ValidationRegex.REGEXP_PASSWORD;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record RegistrationRequestDto(
        @NotNull
        String username,
        @NotNull
        @Size(min = 8, max = 50)
        @Pattern(regexp = REGEXP_PASSWORD)
        String password
) {
}
