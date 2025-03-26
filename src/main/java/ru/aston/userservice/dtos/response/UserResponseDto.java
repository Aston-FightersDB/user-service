package ru.aston.userservice.dtos.response;

import java.time.LocalDateTime;

public record UserResponseDto (
  String userId,
  String username,
  String password,
  LocalDateTime profileRegDate,
  RoleResponseDto role
) {}
