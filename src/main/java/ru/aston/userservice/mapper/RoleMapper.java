package ru.aston.userservice.mapper;

import org.mapstruct.Mapper;
import ru.aston.userservice.dtos.response.RoleResponseDto;
import ru.aston.userservice.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
  Role map(RoleResponseDto dto);
  RoleResponseDto map(Role role);
}
