package org.example.library.business.dao;

import org.example.library.domain.Reservations;

import java.util.List;
import java.util.Optional;

public interface ReservationsDao {

    Reservations saveReservations(Reservations reservation);

    List<Reservations> findAllByUserId(Integer userId);

    List<Reservations> findByUserId(Integer userId);

    Optional<Reservations> findById(Integer reservationId);

    List<Reservations> findAll();
}
