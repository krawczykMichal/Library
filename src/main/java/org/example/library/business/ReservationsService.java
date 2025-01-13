package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.ReservationsDao;
import org.example.library.domain.Books;
import org.example.library.domain.Cart;
import org.example.library.domain.Reservations;
import org.example.library.domain.Users;
import org.example.library.domain.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationsService {

    private final ReservationsDao reservationsDao;

    @Transactional
    public void makeReservation(Cart cart) {
        Reservations reservation = Reservations.builder()
                .cart(cart)
                .reservationDate(LocalDateTime.now().plusDays(3L))
                .build();
        reservationsDao.saveReservations(reservation);
    }

    public Reservations findByUserId(Integer userId) {
        Optional<Reservations> reservation = reservationsDao.findByUserId(userId);
        if (reservation.isEmpty()) {
            throw new NotFoundException("Could not find reservation: " + reservation);
        }
        return reservation.get();
    }

}
