package com.jaroso.productosapipractica.services;

import com.jaroso.productosapipractica.dtos.LoteCreateDto;
import com.jaroso.productosapipractica.dtos.LoteDto;
import com.jaroso.productosapipractica.dtos.LoteUpdateDto;
import com.jaroso.productosapipractica.entities.Lote;
import com.jaroso.productosapipractica.entities.Producto;
import com.jaroso.productosapipractica.mappers.LoteMapper;
import com.jaroso.productosapipractica.repositories.LoteRepository;
import com.jaroso.productosapipractica.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LoteServiceImpl implements LoteService {

    @Autowired
    private LoteRepository loteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private LoteMapper loteMapper;

    @Override
    @Transactional
    public LoteDto save(LoteCreateDto dto, Long productoId) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto con id " + productoId + " no encontrado"));

        Lote lote = loteMapper.loteCreateDtoToEntity(dto);
        lote.setProducto(producto);

        return loteMapper.toDto(loteRepository.save(lote));
    }

    @Override
    @Transactional
    public LoteDto update(LoteUpdateDto dto) {
        Lote lote = loteRepository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Lote con id " + dto.id() + " no encontrado"));

        // Solo actualizamos el estado, que es lo que pide el PATCH
        lote.setEstado(dto.estado());

        return loteMapper.toDto(loteRepository.save(lote));
    }

    @Override
    public Optional<LoteDto> findById(Long id) {
        return loteRepository.findById(id).map(loteMapper::toDto);
    }

    @Override
    public List<LoteDto> findAllByProductoId(Long productoId) {
        if (!productoRepository.existsById(productoId)) {
            throw new RuntimeException("Producto con id " + productoId + " no encontrado");
        }
        return loteRepository.findAllByProductoId(productoId)
                .stream()
                .map(loteMapper::toDto)
                .toList();
    }

    @Override
    public boolean delete(Long id) {
        Optional<Lote> lote = loteRepository.findById(id);
        if (lote.isPresent()) {
            loteRepository.delete(lote.get());
            return true;
        }
        return false;
    }
}