package com.jaroso.productosapipractica.security;


import com.jaroso.productosapipractica.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //Paso 1: Extraer token de la cabecera HTTP
        String token = extractToken(request);

        //Paso 2: Validar token
        if (this.jwtService.isValidToken(token)) {
            //Paso 3: Si el token es válido, sacamos el usuario
            //Si el token no es válido salta excepción
            String username = this.jwtService.getUsernameFromToken(token);
            UserDetails user = this.userService.loadUserByUsername(username);

            //Paso 4: Generar objeto Authentication y meterlo en el SecurityContext
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword(),
                    user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        //Continuar con la cadena de filtros
        filterChain.doFilter(request, response);

    }

    //Extraer token de la cabecera HTTP
    private String extractToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");

        //Asegurarnos que es un bearer token
        if (StringUtils.hasLength(bearerToken) && bearerToken.startsWith("Bearer")){
            return bearerToken.substring("Bearer ".length());
        }

        return null;
    }
}
