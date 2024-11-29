package org.example.library.business.dao;

import org.example.library.domain.Reservations;

import java.util.Optional;

public interface ReservationsDao {

    Reservations saveReservations(Reservations reservation);

    Optional<Reservations> findByUserId(Integer userId);
}
