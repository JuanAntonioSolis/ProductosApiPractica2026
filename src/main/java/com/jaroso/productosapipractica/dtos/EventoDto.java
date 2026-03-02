package com.jaroso.productosapipractica.dtos;

import java.time.LocalDateTime;

public record EventoDto(Long id, LocalDateTime timestamp, String tipoEvento,
                        String ubicacion, String observaciones, LoteDto lote) {
}
