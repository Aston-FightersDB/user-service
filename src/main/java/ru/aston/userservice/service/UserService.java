package ru.aston.userservice.service;

import java.util.List;
import ru.aston.userservice.dtos.response.UserResponseDto;

public interface UserService {
  List<UserResponseDto> getAllUsers();
}
