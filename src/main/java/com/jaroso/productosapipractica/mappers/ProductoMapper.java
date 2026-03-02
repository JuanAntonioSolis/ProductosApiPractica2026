package com.jaroso.productosapipractica.mappers;

import com.jaroso.productosapipractica.dtos.ProductoCreateDto;
import com.jaroso.productosapipractica.dtos.ProductoDto;
import com.jaroso.productosapipractica.dtos.ProductoUpdateDto;
import com.jaroso.productosapipractica.entities.Producto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoDto toDto(Producto producto);
    Producto productoCreateDtoToEntity(ProductoCreateDto dto);
    Producto updateToEntity(ProductoUpdateDto dto);

}