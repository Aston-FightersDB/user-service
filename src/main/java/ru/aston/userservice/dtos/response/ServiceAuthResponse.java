package ru.aston.userservice.dtos.response;

public record ServiceAuthResponse (
  MessageResponseDto messageResponseDto,
  String token)
{}