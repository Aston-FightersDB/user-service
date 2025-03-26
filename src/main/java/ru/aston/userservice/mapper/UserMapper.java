package ru.aston.userservice.mapper;

import org.mapstruct.Mapper;
import ru.aston.userservice.dtos.request.RegistrationRequestDto;
import ru.aston.userservice.dtos.response.RegistrationResponseDto;
import ru.aston.userservice.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
  User map(RegistrationRequestDto dto);
  RegistrationResponseDto map(User entity);
}
