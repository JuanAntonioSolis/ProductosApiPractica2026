package com.jaroso.productosapipractica.mappers;

import com.jaroso.productosapipractica.dtos.LoteCreateDto;
import com.jaroso.productosapipractica.dtos.LoteDto;
import com.jaroso.productosapipractica.dtos.LoteUpdateDto;
import com.jaroso.productosapipractica.entities.Lote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface LoteMapper {

    LoteDto toDto(Lote lote);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "producto", ignore = true)
    Lote loteCreateDtoToEntity(LoteCreateDto dto);
    Lote updateToEntity(LoteUpdateDto dto);

}
