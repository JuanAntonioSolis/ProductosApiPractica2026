package com.jaroso.productosapipractica.dtos;

import java.util.List;

public record AuthDto(String username, List<String> authorities, String token) {
}
