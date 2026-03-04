package com.jaroso.productosapipractica.controllers;

import com.jaroso.productosapipractica.dtos.LoteCreateDto;
import com.jaroso.productosapipractica.dtos.LoteDto;
import com.jaroso.productosapipractica.dtos.LoteUpdateDto;
import com.jaroso.productosapipractica.entities.Lote;
import com.jaroso.productosapipractica.repositories.LoteRepository;
import com.jaroso.productosapipractica.services.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class LoteController {

    @Autowired
    private LoteService loteService;

    @Autowired
    private LoteRepository loteRepository;

    @GetMapping("/api/productos/{productoId}/lotes")
    public ResponseEntity<List<LoteDto>> findAllByProducto(@PathVariable Long productoId) {
        return ResponseEntity.ok(loteService.findAllByProductoId(productoId));
    }

    @GetMapping("/api/lotes/{id}")
    public ResponseEntity<LoteDto> findById(@PathVariable Long id) {
        Optional<LoteDto> loteDto = loteService.findById(id);
        return loteDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/api/lotes/{id}")
    public ResponseEntity<LoteDto> deleteById(@PathVariable long id){
        boolean encontrado = loteService.delete(id);

        if(encontrado){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/productos/{productoId}/lotes")
    public ResponseEntity<LoteDto> save(@PathVariable Long productoId, @RequestBody LoteCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loteService.save(dto, productoId));
    }

    @PatchMapping("/api/lotes/{id}/estado")
    public ResponseEntity<LoteDto> updateEstado(@PathVariable Long id, @RequestBody LoteUpdateDto dto) {
        return ResponseEntity.ok(loteService.update(dto));
    }

}
