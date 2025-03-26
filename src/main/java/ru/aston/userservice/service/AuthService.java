package ru.aston.userservice.service;


import ru.aston.userservice.dtos.request.AuthorizationRequestDto;
import ru.aston.userservice.dtos.request.RegistrationRequestDto;
import ru.aston.userservice.dtos.response.RegistrationResponseDto;
import ru.aston.userservice.dtos.response.ServiceAuthResponse;

public interface AuthService {

  ServiceAuthResponse authorizeUser(AuthorizationRequestDto authorizationRequestDto);

  RegistrationResponseDto registerNewUser(RegistrationRequestDto registrationRequestDto);

}
