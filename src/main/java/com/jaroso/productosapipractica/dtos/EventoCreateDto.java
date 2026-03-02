package com.jaroso.productosapipractica.dtos;

import java.time.LocalDateTime;

public record EventoCreateDto(LocalDateTime timestamp, String tipoEvento,
                              String ubicacion, String observaciones, LoteDto lote) {
}
