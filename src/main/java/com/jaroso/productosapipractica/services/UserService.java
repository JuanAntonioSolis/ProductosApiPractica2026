package com.jaroso.productosapipractica.services;


import com.jaroso.productosapipractica.dtos.UserCreateDto;
import com.jaroso.productosapipractica.dtos.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService {

    //RETRIEVE
    List<UserDto> findAll();
    Optional<UserDto> findById(Long id);
    Optional<UserDto> findByUsername(String username);

    //CREATE/UPDATE
    UserDto saveUser(UserCreateDto userDto);

    //DELETE
    void deleteUser(Long id);

    UserDetails loadUserByUsername(String username);

}
