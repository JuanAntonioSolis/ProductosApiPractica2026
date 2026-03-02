package com.jaroso.productosapipractica.dtos;

import java.time.LocalDate;

public record LoteCreateDto(Integer numeroLote,
                            LocalDate fechaProduccion, Integer cantidad,
                            String estado, ProductoDto producto) {
}
