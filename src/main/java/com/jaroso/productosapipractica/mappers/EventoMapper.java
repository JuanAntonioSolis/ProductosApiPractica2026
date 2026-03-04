package com.jaroso.productosapipractica.mappers;

import com.jaroso.productosapipractica.dtos.EventoCreateDto;
import com.jaroso.productosapipractica.dtos.EventoDto;
import com.jaroso.productosapipractica.entities.EventoTrazabilidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
//, uses = {LoteMapper.class}
public interface EventoMapper {


    EventoDto toDto(EventoTrazabilidad evento);

    @Mapping(target = "lote", ignore = true)
    @Mapping(target = "timestamp", expression = "java(java.time.LocalDateTime.now())")
    EventoTrazabilidad eventoCreateDtoToEntity(EventoCreateDto dto);

}
