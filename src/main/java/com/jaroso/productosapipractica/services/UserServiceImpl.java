package com.jaroso.productosapipractica.services;


import com.jaroso.productosapipractica.dtos.UserCreateDto;
import com.jaroso.productosapipractica.dtos.UserDto;
import com.jaroso.productosapipractica.entities.User;
import com.jaroso.productosapipractica.mappers.UserMapper;
import com.jaroso.productosapipractica.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper mapper;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id).map(mapper::toDto);
    }

    @Override
    public Optional<UserDto> findByUsername(String username) {
        return userRepository.findByUsername(username).map(mapper::toDto);
    }

    @Override
    public UserDto saveUser(UserCreateDto user) {
        User userEntity = mapper.toEntity(user);

        return mapper.toDto(userRepository.save(userEntity));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(username + " no encontrado")
        );
    }
}
