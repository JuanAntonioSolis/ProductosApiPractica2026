package com.jaroso.productosapipractica.services;



import com.jaroso.productosapipractica.dtos.AuthDto;
import com.jaroso.productosapipractica.dtos.UserCreateDto;
import com.jaroso.productosapipractica.dtos.UserDto;
import com.jaroso.productosapipractica.dtos.UserLoginDto;
import com.jaroso.productosapipractica.entities.User;
import com.jaroso.productosapipractica.repositories.UserRepository;
import com.jaroso.productosapipractica.security.JwtService;
import com.jaroso.productosapipractica.security.UserAuthority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;


    /**
     * Registra un nuevo usuario en BBDD
     * @param userDTO
     * @return
     */
    public UserDto save(UserCreateDto userDTO) {
        User user = new User(
                null,
                userDTO.username(),
                passwordEncoder.encode(userDTO.password()),
                userDTO.email(),
                List.of(UserAuthority.READ)
        );

        // Set the "nombre" field provided in the DTO (was missing)
        user.setNombre(userDTO.nombre());

        //Comprobar que el username no esté ya en la BBDD
        if (userRepository.findByUsername(userDTO.username()).isPresent()) {
            log.error("El usuario ya existe");
            throw new RuntimeException("El usuario ya existe");
        }

        //Si no existe se inserta en BBDD y devolvemos UserDto
        this.userRepository.save(user);

        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getNombre());
    }

    /**
     * Login de un usuario
     * @param user
     * @return AuthDto con el Token JWT
     */
    public ResponseEntity<AuthDto> login(UserLoginDto user) {

        //1. Buscar usuario en bbdd
        Optional<User> userOptional = this.userRepository.findByUsername(user.username());
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        //2. Comprobar que la contraseña concida con la del usuario

        Authentication authDTO = new UsernamePasswordAuthenticationToken(user.username(), user.password());
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.username(), user.password())
                );

        //3. Generar token JWT
        String token = jwtService.generateToken(authentication);

        //4. Generar el objeto AuthDto con el token JWT y devolverlo en la respuesta http
        User userEntity = (User) authentication.getPrincipal();
        AuthDto auth = new AuthDto(userEntity.getUsername(),
                userEntity.getAuthorities().stream().map(Object::toString).toList(),token);

        return ResponseEntity.ok(auth);
    }



}
