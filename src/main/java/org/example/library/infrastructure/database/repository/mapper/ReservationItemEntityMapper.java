package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.ReservationItem;
import org.example.library.infrastructure.database.entity.ReservationItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationItemEntityMapper {

    ReservationItemEntity mapToReservationItemEntity(ReservationItem reservationItem);

    ReservationItem mapFromReservationItemEntity(ReservationItemEntity reservationItem);

    List<ReservationItemEntity> mapToReservationItemEntityList(List<ReservationItem> reservationItem);

    List<ReservationItem> mapFromReservationItemEntityList(List<ReservationItemEntity> reservationItem);
}
