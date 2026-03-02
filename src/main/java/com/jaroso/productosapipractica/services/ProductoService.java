package com.jaroso.productosapipractica.services;

import com.jaroso.productosapipractica.dtos.ProductoCreateDto;
import com.jaroso.productosapipractica.dtos.ProductoDto;
import com.jaroso.productosapipractica.dtos.ProductoUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    public ProductoDto save(ProductoCreateDto productoDto);
    public ProductoDto update(ProductoUpdateDto productoDto);
    public List<ProductoDto> findAll();
    public Optional<ProductoDto> findById(Long id);
    public boolean delete(Long id);

}
