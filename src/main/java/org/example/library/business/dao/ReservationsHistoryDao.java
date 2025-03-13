package org.example.library.business.dao;

import org.example.library.domain.Reservations;
import org.example.library.domain.ReservationsHistory;

import java.util.List;
import java.util.Optional;

public interface ReservationsHistoryDao {

    ReservationsHistory saveReservationsHistory(Reservations reservation);

    List<ReservationsHistory> findAllByUserId(Integer userId);

    Optional<ReservationsHistory> findByReservationNumber(String reservationNumber);
}
