package ru.aston.userservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aston.userservice.dtos.request.RegistrationRequestDto;
import ru.aston.userservice.dtos.request.UpdateRequestDto;
import ru.aston.userservice.dtos.response.RegistrationResponseDto;
import ru.aston.userservice.dtos.response.UserResponseDto;
import ru.aston.userservice.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

  User map(UserResponseDto dto);
  UserResponseDto map(User entity);
  User map(UpdateRequestDto dto);
}
