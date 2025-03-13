package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.ReservationsDao;
import org.example.library.domain.Reservations;
import org.example.library.infrastructure.database.entity.ReservationsEntity;
import org.example.library.infrastructure.database.repository.jpa.ReservationsJpaRepository;
import org.example.library.infrastructure.database.repository.mapper.ReservationsEntityMapper;
import org.example.library.infrastructure.database.repository.mapper.ReservationsEntityMapperClass;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class ReservationsRepository implements ReservationsDao {

    private final ReservationsJpaRepository reservationsJpaRepository;
    private final ReservationsEntityMapper reservationsEntityMapper;

    private final ReservationsEntityMapperClass reservationsEntityMapperClass;

    @Override
    public Reservations saveReservations(Reservations reservation) {
        ReservationsEntity toSave = reservationsEntityMapper.mapToReservationsEntity(reservation);
        ReservationsEntity saved = reservationsJpaRepository.save(toSave);
        return reservationsEntityMapper.mapFromReservationsEntity(saved);
    }

    @Override
    public List<Reservations> findAllByUserId(Integer userId) {
        return reservationsJpaRepository.findAllByUserId(userId).stream().map(reservationsEntityMapper::mapFromReservationsEntity).toList();

    }

    @Override
    public List<Reservations> findByUserId(Integer userId) {
        return reservationsJpaRepository.findByUserId(userId).stream().map(reservationsEntityMapper::mapFromReservationsEntity).toList();
    }

    @Override
    public Optional<Reservations> findById(Integer reservationId) {
        return reservationsJpaRepository.findById(reservationId).map(reservationsEntityMapper::mapFromReservationsEntity);
    }

    @Override
    public List<Reservations> findAll() {
        return reservationsJpaRepository.findAll().stream().map(reservationsEntityMapper::mapFromReservationsEntity).toList();
    }

    @Override
    public void deleteById(Integer reservationId) {
        reservationsJpaRepository.deleteById(reservationId);
    }

    @Override
    public Optional<Reservations> findByReservationNumber(String reservationNumber) {
        return reservationsJpaRepository.findByReservationNumber(reservationNumber).map(reservationsEntityMapperClass::mapFromReservationsEntity);
    }

    @Override
    public void deleteByCartUserId(Integer userId) {
        reservationsJpaRepository.deleteByCartUserId(userId);
    }

    @Override
    public void deleteByReservationNumber(String reservationNumber) {
        reservationsJpaRepository.deleteByReservationNumber(reservationNumber);
    }
}
