package com.jaroso.productosapipractica.repositories;

import com.jaroso.productosapipractica.entities.EventoTrazabilidad;
import com.jaroso.productosapipractica.entities.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<EventoTrazabilidad, Long> {

    //Historia de eventos de un lote
    List<EventoTrazabilidad> findAllEventosByLoteId(Long loteId);

    //Eventos ordenados por fecha
    List<EventoTrazabilidad> findAllEventosByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);

    //Filtro eventos por tipo y rango de fechas
    List<EventoTrazabilidad> findAllEventosByTipoEventoAndTimestampBetween(String tipoEvento,
                                                       LocalDateTime startDate,
                                                       LocalDateTime endDate);

}
