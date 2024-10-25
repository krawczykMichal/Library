package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.Reservations;
import org.example.library.infrastructure.database.entity.ReservationsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationsEntityMapper {

    Reservations mapFromReservationsEntity(ReservationsEntity reservationsEntity);

    ReservationsEntity mapToReservationsEntity(Reservations reservations);
}
