package com.jaroso.productosapipractica.controllers;

import com.jaroso.productosapipractica.dtos.ProductoCreateDto;
import com.jaroso.productosapipractica.dtos.ProductoDto;
import com.jaroso.productosapipractica.dtos.ProductoUpdateDto;
import com.jaroso.productosapipractica.repositories.ProductoRepository;
import com.jaroso.productosapipractica.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public ResponseEntity<List<ProductoDto>> findAll() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> findById(@PathVariable Long id) {
        Optional<ProductoDto> productoDto = productoService.findById(id);

        return productoDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductoDto> deleteById(@PathVariable Long id) {
        boolean econtrado = productoService.delete(id);
        if (econtrado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductoDto> save(@RequestBody ProductoCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(dto));
    }

    @PutMapping
    public ResponseEntity<ProductoDto> update(@RequestBody ProductoUpdateDto dto) {
        return ResponseEntity.ok(productoService.update(dto));
    }
}
