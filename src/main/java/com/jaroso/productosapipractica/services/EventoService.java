package com.jaroso.productosapipractica.services;

import com.jaroso.productosapipractica.dtos.EventoCreateDto;
import com.jaroso.productosapipractica.dtos.EventoDto;
import com.jaroso.productosapipractica.entities.EventoTrazabilidad;

import java.time.LocalDateTime;
import java.util.List;

public interface EventoService {

    EventoDto save(EventoCreateDto eventoDto, Long lote_id);
    List<EventoDto> findAllByLoteId(Long lote_id);
    List<EventoDto> findAllByRangoFechas(LocalDateTime inicio, LocalDateTime fin);
    List<EventoDto> filtrarTipoYFechas(String tipoEvento, LocalDateTime inicio, LocalDateTime fin);

}
