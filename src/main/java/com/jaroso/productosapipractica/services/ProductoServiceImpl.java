package com.jaroso.productosapipractica.services;

import com.jaroso.productosapipractica.dtos.ProductoCreateDto;
import com.jaroso.productosapipractica.dtos.ProductoDto;
import com.jaroso.productosapipractica.dtos.ProductoUpdateDto;
import com.jaroso.productosapipractica.entities.Producto;
import com.jaroso.productosapipractica.mappers.ProductoMapper;
import com.jaroso.productosapipractica.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoMapper productoMapper;

    @Override
    public ProductoDto save(ProductoCreateDto productoDto) {
        Producto producto = productoMapper.productoCreateDtoToEntity(productoDto);

        return productoMapper.toDto(productoRepository.save(producto));
    }

    @Override
    public ProductoDto update(ProductoUpdateDto producto) {
        Producto productoEntity = productoMapper.updateToEntity(producto);

        return productoMapper.toDto(productoRepository.save(productoEntity));
    }

    @Override
    //@Transactional(readOnly = true)
    public List<ProductoDto> findAll() {
        return productoRepository.findAll()
                .stream()
                .map(productoMapper::toDto)
                .toList();
    }

    @Override
    public Optional<ProductoDto> findById(Long id) {
        return productoRepository.findById(id).map(productoMapper::toDto);
    }

    @Override
    public boolean delete(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);

        if (producto.isPresent()) {
            productoRepository.delete(producto.get());
            return true;
        } else {
            return false;
        }
    }
}
