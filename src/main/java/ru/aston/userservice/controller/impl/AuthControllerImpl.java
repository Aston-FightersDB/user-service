package ru.aston.userservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import ru.aston.userservice.controller.AuthController;
import ru.aston.userservice.dtos.request.AuthorizationRequestDto;
import ru.aston.userservice.dtos.request.RegistrationRequestDto;
import ru.aston.userservice.dtos.response.MessageResponseDto;
import ru.aston.userservice.dtos.response.RegistrationResponseDto;
import ru.aston.userservice.dtos.response.ServiceAuthResponse;
import ru.aston.userservice.service.AuthService;

@Controller
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

  private final AuthService authService;

  @Override
  public ResponseEntity<RegistrationResponseDto> register(
      RegistrationRequestDto registrationRequestDto) {
    return ResponseEntity.ok(authService.registerNewUser(registrationRequestDto));
  }

  @Override
  public ResponseEntity<MessageResponseDto> authorize(
      AuthorizationRequestDto authorizationRequestDto) {
    ServiceAuthResponse authResponse = authService.authorizeUser(authorizationRequestDto);
    String token = authResponse.token();
    MessageResponseDto dto = authResponse.messageResponseDto();
    return ResponseEntity.ok()
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        .body(dto);  }
}
