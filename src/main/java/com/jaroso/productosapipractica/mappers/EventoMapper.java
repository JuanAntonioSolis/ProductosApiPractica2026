package com.jaroso.productosapipractica.mappers;

import com.jaroso.productosapipractica.dtos.EventoCreateDto;
import com.jaroso.productosapipractica.dtos.EventoDto;
import com.jaroso.productosapipractica.entities.EventoTrazabilidad;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
//, uses = {LoteMapper.class}
public interface EventoMapper {
    EventoDto toDto(EventoTrazabilidad evento);
    EventoTrazabilidad eventoCreateDtoToEntity(EventoCreateDto dto);

}
