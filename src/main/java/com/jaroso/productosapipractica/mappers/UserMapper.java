package com.jaroso.productosapipractica.mappers;

import com.jaroso.productosapipractica.dtos.UserCreateDto;
import com.jaroso.productosapipractica.dtos.UserDto;
import com.jaroso.productosapipractica.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
    User toEntity(UserCreateDto userDto);
}
