package com.jaroso.productosapipractica.dtos;

import java.time.LocalDate;

public record LoteDto(Long id, Integer numeroLote,
                      LocalDate fechaProduccion, Integer cantidad,
                      String estado, ProductoDto producto) {
}
