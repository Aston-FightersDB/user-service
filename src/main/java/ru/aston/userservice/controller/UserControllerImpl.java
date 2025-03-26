package ru.aston.userservice.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import ru.aston.userservice.dtos.response.UserResponseDto;
import ru.aston.userservice.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

  private final UserService userService;

  @Override
  public ResponseEntity<List<UserResponseDto>> register() {
    return ResponseEntity.ok(userService.getAllUsers());
  }
}
