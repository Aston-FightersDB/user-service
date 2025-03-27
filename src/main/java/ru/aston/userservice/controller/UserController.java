package ru.aston.userservice.controller;


import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aston.userservice.dtos.request.UpdateRequestDto;
import ru.aston.userservice.dtos.response.UserResponseDto;

@RequestMapping("/user-service")
@Validated
public interface UserController {
  @GetMapping("/users")
  ResponseEntity<List<UserResponseDto>> viewUsers();

  @PutMapping("/users")
  ResponseEntity<UserResponseDto> updateUser(@Valid @RequestBody UpdateRequestDto updateRequestDto);
}
