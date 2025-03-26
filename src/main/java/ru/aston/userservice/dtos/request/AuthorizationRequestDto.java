package ru.aston.userservice.dtos.request;

import static ru.aston.userservice.validation.ValidationRegex.REGEXP_PASSWORD;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AuthorizationRequestDto(
    @NotNull
    String username,
    @Pattern(regexp = REGEXP_PASSWORD, message = """
        Пароль должен быть от 8 до 50 символов в длину, содержать хотя бы одну заглавную
        букву от A до Z, одну строчную букву от a до z и один из специальных символов
         (~ ! ? @ # $ % ^ & * _ - + ( ) [ ] { } > < / \\ | " ' . , : ;)""")
    @NotNull
    String password
) {

}