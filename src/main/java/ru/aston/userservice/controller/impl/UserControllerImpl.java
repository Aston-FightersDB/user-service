package ru.aston.userservice.controller.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import ru.aston.userservice.controller.UserController;
import ru.aston.userservice.dtos.request.UpdateRequestDto;
import ru.aston.userservice.dtos.response.UserResponseDto;
import ru.aston.userservice.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

  private final UserService userService;

  @Override
  public ResponseEntity<List<UserResponseDto>> viewUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @Override
  public ResponseEntity<UserResponseDto> updateUser(UpdateRequestDto updateRequestDto) {
    return ResponseEntity.ok(userService.updateUser(updateRequestDto));
  }


}
