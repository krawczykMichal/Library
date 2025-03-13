package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.ReservationsHistoryDao;
import org.example.library.domain.Reservations;
import org.example.library.domain.ReservationsHistory;
import org.example.library.domain.mapper.ReservationsHistoryMapper;
import org.example.library.infrastructure.database.entity.ReservationsHistoryEntity;
import org.example.library.infrastructure.database.repository.jpa.ReservationsHistoryJpaRepository;
import org.example.library.infrastructure.database.repository.mapper.ReservationsHistoryEntityMapperClass;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ReservationsHistoryRepository implements ReservationsHistoryDao {

    private final ReservationsHistoryJpaRepository reservationsHistoryJpaRepository;
    private final ReservationsHistoryEntityMapperClass reservationsHistoryEntityMapperClass;
    private final ReservationsHistoryMapper reservationsHistoryMapper;

    @Override
    public ReservationsHistory saveReservationsHistory(Reservations reservation) {
        ReservationsHistory reservationsHistory = reservationsHistoryMapper.mapFromReservations(reservation);
        ReservationsHistoryEntity toSave = reservationsHistoryEntityMapperClass.mapToReservationsEntity(reservationsHistory);
        ReservationsHistoryEntity saved = reservationsHistoryJpaRepository.save(toSave);
        return reservationsHistoryEntityMapperClass.mapFromReservationsHistoryEntity(saved);
    }

    @Override
    public List<ReservationsHistory> findAllByUserId(Integer userId) {
        return reservationsHistoryJpaRepository.findAllByUserId(userId).stream().map(reservationsHistoryEntityMapperClass::mapFromReservationsHistoryEntity).toList();
    }

    @Override
    public Optional<ReservationsHistory> findByReservationNumber(String reservationNumber) {
        return reservationsHistoryJpaRepository.findByReservationNumber(reservationNumber).map(reservationsHistoryEntityMapperClass::mapFromReservationsHistoryEntity);
    }
}
