package com.jaroso.productosapipractica.services;

import com.jaroso.productosapipractica.dtos.LoteCreateDto;
import com.jaroso.productosapipractica.dtos.LoteDto;
import com.jaroso.productosapipractica.dtos.LoteUpdateDto;
import com.jaroso.productosapipractica.entities.Lote;
import com.jaroso.productosapipractica.mappers.LoteMapper;
import com.jaroso.productosapipractica.repositories.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoteServiceImpl implements LoteService {

    @Autowired
    private LoteRepository repo;

    @Autowired
    private LoteMapper mapper;

    @Override
    public LoteDto save(LoteCreateDto loteDto) {
        Lote lote = mapper.loteCreateDtoToEntity(loteDto);

        return mapper.toDto(repo.save(lote));
    }

    @Override
    public LoteDto update(LoteUpdateDto loteDto) {
        Lote loteEntity = mapper.updateToEntity(loteDto);

        return mapper.toDto(repo.save(loteEntity));
    }

    @Override
    public List<LoteDto> findAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public Optional<LoteDto> findById(Long id) {
        return repo.findById(id).map(mapper::toDto);
    }

    @Override
    public boolean delete(Long id) {
        Optional<Lote> lote = repo.findById(id);

        if (lote.isPresent()) {
            repo.delete(lote.get());
            return true;
        } else {
            return false;
        }
    }
}
