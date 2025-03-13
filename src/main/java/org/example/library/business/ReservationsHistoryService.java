package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.ReservationsHistoryDao;
import org.example.library.domain.ReservationsHistory;
import org.example.library.domain.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationsHistoryService {

    private final ReservationsHistoryDao reservationsHistoryDao;


    public List<ReservationsHistory> findAllByUserId(Integer userId) {
        return reservationsHistoryDao.findAllByUserId(userId);
    }

    public ReservationsHistory findByReservationNumber(String reservationNumber) {
        Optional<ReservationsHistory> byReservationNumber = reservationsHistoryDao.findByReservationNumber(reservationNumber);
        if (byReservationNumber.isEmpty()) {
            throw new NotFoundException("Reservation not found by reservationNumber: " + reservationNumber);
        }
        return byReservationNumber.get();
    }
}
