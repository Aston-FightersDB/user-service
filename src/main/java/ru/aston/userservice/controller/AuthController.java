package ru.aston.userservice.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aston.userservice.dtos.request.AuthorizationRequestDto;
import ru.aston.userservice.dtos.request.RegistrationRequestDto;
import ru.aston.userservice.dtos.response.MessageResponseDto;
import ru.aston.userservice.dtos.response.RegistrationResponseDto;

@RequestMapping("/user-service")
@Validated
public interface AuthController {
  @PostMapping("/auth/registration")
  ResponseEntity<RegistrationResponseDto> register(
      @RequestBody @Valid RegistrationRequestDto registrationRequestDto);

  @PostMapping("/auth/login")
  ResponseEntity<MessageResponseDto> authorize(
      @RequestBody @Valid AuthorizationRequestDto authorizationRequestDto);
}