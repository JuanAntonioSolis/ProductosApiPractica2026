package com.jaroso.productosapipractica.controllers;

import com.jaroso.productosapipractica.dtos.EventoCreateDto;
import com.jaroso.productosapipractica.dtos.EventoDto;
import com.jaroso.productosapipractica.repositories.EventoRepository;
import com.jaroso.productosapipractica.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/lotes/{lote_id}")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private EventoRepository eventoRepository;

    @PostMapping("/eventos")
    public ResponseEntity<EventoDto> save(@RequestBody EventoCreateDto dto,@PathVariable Long lote_id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoService.save(dto, lote_id));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<EventoDto>> findEventos(
            @PathVariable Long lote_id,
            @RequestParam(required = false) String tipoEvento,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {

        if (tipoEvento != null && inicio != null && fin != null) {
            return ResponseEntity.ok(eventoService.filtrarTipoYFechas(tipoEvento, inicio, fin));
        }
        if (inicio != null && fin != null) {
            return ResponseEntity.ok(eventoService.findAllByRangoFechas(inicio, fin));
        }
        return ResponseEntity.ok(eventoService.findAllByLoteId(lote_id));
    }

}
