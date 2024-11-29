package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.ReservationsDao;
import org.example.library.domain.Reservations;
import org.example.library.infrastructure.database.entity.ReservationsEntity;
import org.example.library.infrastructure.database.repository.jpa.ReservationsJpaRepository;
import org.example.library.infrastructure.database.repository.mapper.ReservationsEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class ReservationsRepository implements ReservationsDao {

    private final ReservationsJpaRepository reservationsJpaRepository;
    private final ReservationsEntityMapper reservationsEntityMapper;

    @Override
    public Reservations saveReservations(Reservations reservation) {
        ReservationsEntity toSave = reservationsEntityMapper.mapToReservationsEntity(reservation);
        ReservationsEntity saved = reservationsJpaRepository.save(toSave);
        return reservationsEntityMapper.mapFromReservationsEntity(saved);
    }

    @Override
    public Optional<Reservations> findByUserId(Integer userId) {
        return reservationsJpaRepository.findByUserId(userId).map(reservationsEntityMapper::mapFromReservationsEntity);
    }
}
