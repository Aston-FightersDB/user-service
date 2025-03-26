package ru.aston.userservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.userservice.dtos.response.UserResponseDto;
import ru.aston.userservice.entity.User;
import ru.aston.userservice.exception.AppException;
import ru.aston.userservice.exception.EnumException;
import ru.aston.userservice.mapper.UserMapper;
import ru.aston.userservice.repository.UserRepository;
import ru.aston.userservice.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  @Override
  public List<UserResponseDto> getAllUsers() {
    List<User> users = userRepository.findAll();
    if (users.isEmpty()) {
      throw new AppException(EnumException.NOT_FOUND, "No users found");
    }
    return users.stream().map(userMapper::map).collect(Collectors.toList());
  }
}
