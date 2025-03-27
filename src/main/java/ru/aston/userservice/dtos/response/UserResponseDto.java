package ru.aston.userservice.dtos.response;

import java.time.LocalDateTime;
import java.util.List;

public record UserResponseDto (
  String userId,
  String username,
  String password,
  LocalDateTime profileRegDate,
  List<RoleResponseDto> roles
) {}
