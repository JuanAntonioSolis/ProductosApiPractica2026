package com.jaroso.productosapipractica.services;

import com.jaroso.productosapipractica.dtos.LoteCreateDto;
import com.jaroso.productosapipractica.dtos.LoteDto;
import com.jaroso.productosapipractica.dtos.LoteUpdateDto;

import java.util.List;
import java.util.Optional;

public interface LoteService {

    LoteDto save(LoteCreateDto loteDto);
    LoteDto update(LoteUpdateDto loteDto);
    List<LoteDto> findAll();
    Optional<LoteDto> findById(Long id);
    boolean delete(Long id);

}
