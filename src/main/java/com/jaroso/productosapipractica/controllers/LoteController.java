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
@RequestMapping("/lotes")
public class LoteController {

    @Autowired
    private LoteService loteService;

    @Autowired
    private LoteRepository loteRepository;

    @GetMapping
    public ResponseEntity<List<Lote>> findAll() {
        return ResponseEntity.ok(loteRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoteDto> findById(@PathVariable long id){
        Optional<LoteDto> loteDto = loteService.findById(id);

        return loteDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LoteDto> deleteById(@PathVariable long id){
        boolean encontrado = loteService.delete(id);

        if(encontrado){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<LoteDto> save(@RequestBody LoteCreateDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(loteService.save(dto));
    }

    @PutMapping
    public ResponseEntity<LoteDto> update(@RequestBody LoteUpdateDto dto){
        return ResponseEntity.ok(loteService.update(dto));
    }

}
