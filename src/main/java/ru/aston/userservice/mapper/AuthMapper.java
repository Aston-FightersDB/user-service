package ru.aston.userservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.aston.userservice.dtos.request.RegistrationRequestDto;
import ru.aston.userservice.dtos.response.RegistrationResponseDto;
import ru.aston.userservice.entity.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthMapper {
  User map(RegistrationRequestDto dto);
  RegistrationResponseDto map(User entity);
}
