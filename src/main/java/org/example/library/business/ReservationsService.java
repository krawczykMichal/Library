package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.ReservationsDao;
import org.example.library.domain.Cart;
import org.example.library.domain.Reservations;
import org.example.library.domain.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationsService {

    private final ReservationsDao reservationsDao;

    @Transactional
    public void makeReservation(Cart cart) {
        Reservations reservation = Reservations.builder()
                .cart(cart)
                .reservationMakeDate(LocalDateTime.now())
                .reservationMakeDate(LocalDateTime.now().plusDays(3L))
                .build();
        reservationsDao.saveReservations(reservation);
    }

    public List<Reservations> findAllByUserId(Integer userId) {
        List<Reservations> reservation = reservationsDao.findAllByUserId(userId);
        if (reservation.isEmpty()) {
            throw new NotFoundException("Could not find reservation: " + reservation);
        }
        return reservation;
    }


    public List<Reservations> findByUserId(Integer userId) {

        return reservationsDao.findByUserId(userId);
    }


    public Reservations findById(Integer reservationId) {
        Optional<Reservations> reservation = reservationsDao.findById(reservationId);
        if (reservation.isEmpty()) {
            throw new NotFoundException("Could not find reservation: " + reservation);
        }
        return reservation.get();
    }

    public List<Reservations> findALl() {

        return reservationsDao.findAll();
    }
}
