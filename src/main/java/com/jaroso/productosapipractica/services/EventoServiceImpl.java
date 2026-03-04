package com.jaroso.productosapipractica.services;

import com.jaroso.productosapipractica.dtos.EventoCreateDto;
import com.jaroso.productosapipractica.dtos.EventoDto;
import com.jaroso.productosapipractica.entities.EventoTrazabilidad;
import com.jaroso.productosapipractica.entities.Lote;
import com.jaroso.productosapipractica.mappers.EventoMapper;
import com.jaroso.productosapipractica.repositories.EventoRepository;
import com.jaroso.productosapipractica.repositories.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EventoMapper eventoMapper;

    @Autowired
    private LoteRepository loteRepository;

    @Override
    @Transactional
    public EventoDto save(EventoCreateDto eventoDto, Long lote_id) {
        Lote lote = loteRepository.findById(lote_id)
                .orElseThrow(() -> new RuntimeException("Lote con id " + lote_id + " no encontrado "));

        EventoTrazabilidad evento = eventoMapper.eventoCreateDtoToEntity(eventoDto);

        evento.setLote(lote);

        return eventoMapper.toDto(eventoRepository.save(evento));

    }

    @Override
    public List<EventoDto> findAllByLoteId(Long lote_id) {
        if (!loteRepository.existsById(lote_id)) {
            throw new RuntimeException("Lote con id " + lote_id + " no encontrado ");
        }

        List<EventoTrazabilidad> eventos = eventoRepository.findAllEventosByLoteId(lote_id);

        return eventos.stream().map(eventoMapper::toDto).toList();
    }

    @Override
    public List<EventoDto> findAllByRangoFechas(LocalDateTime inicio, LocalDateTime fin) {
        List<EventoTrazabilidad> eventos = eventoRepository.findAllEventosByTimestampBetween(inicio, fin);

        return eventos.stream().map(eventoMapper::toDto).toList();
    }

    @Override
    public List<EventoDto> filtrarTipoYFechas(String tipoEvento, LocalDateTime inicio, LocalDateTime fin) {
        List<EventoTrazabilidad> eventos = eventoRepository
                .findAllEventosByTipoEventoAndTimestampBetween(tipoEvento, inicio, fin);

        return eventos.stream().map(eventoMapper::toDto).toList();
    }
}
