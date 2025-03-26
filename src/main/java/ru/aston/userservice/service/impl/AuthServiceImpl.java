package ru.aston.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aston.userservice.dtos.request.AuthorizationRequestDto;
import ru.aston.userservice.dtos.request.RegistrationRequestDto;
import ru.aston.userservice.dtos.response.MessageResponseDto;
import ru.aston.userservice.dtos.response.RegistrationResponseDto;
import ru.aston.userservice.dtos.response.ServiceAuthResponse;
import ru.aston.userservice.entity.Role;
import ru.aston.userservice.entity.User;
import ru.aston.userservice.exception.AppException;
import ru.aston.userservice.exception.EnumException;
import ru.aston.userservice.mapper.UserMapper;
import ru.aston.userservice.repository.UserRepository;
import ru.aston.userservice.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final UserMapper userMapper;

  private final JwtTokenService jwtTokenService;

  @Override
  public ServiceAuthResponse authorizeUser(
      AuthorizationRequestDto authorizationRequestDto) {

    User user = userRepository
        .findByUsername(authorizationRequestDto.username())
        .orElseThrow(() -> new AppException(EnumException.NOT_FOUND, "Email not found"));

    boolean isPasswordMatches = passwordEncoder
        .matches(authorizationRequestDto.password(),
            user.getPassword());

    if (!isPasswordMatches) {
      throw new AppException(EnumException.UNAUTHORIZED, "Incorrect password");
    }

    String token = jwtTokenService.generateAuthToken(user);

    return new ServiceAuthResponse(new MessageResponseDto("Success"), token);
  }

  @Override
  public RegistrationResponseDto registerNewUser(RegistrationRequestDto registrationRequestDto) {
    if (userRepository.existsByUsernameIgnoreCase(registrationRequestDto.username())) {
      throw new AppException(EnumException.BAD_REQUEST,
          "User with this data is already registered");
    }
    User user = userMapper.map(registrationRequestDto);
    user.setPassword(passwordEncoder.encode(registrationRequestDto.password()));

    user.setRole(Role.builder().name("ROLE_USER").build());

    userRepository.save(user);

    return userMapper.map(user);
  }
}
