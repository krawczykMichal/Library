package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.*;
import org.example.library.domain.Cart;
import org.example.library.domain.CartItem;
import org.example.library.domain.ReservationItem;
import org.example.library.domain.Reservations;
import org.example.library.domain.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationsService {

    private final ReservationsDao reservationsDao;
    private final ReservationsHistoryDao reservationsHistoryDao;
    private final ReservationItemDao reservationItemDao;
    private final LoanRequestItemDao loanRequestItemDao;
    private final LoanRequestDao loanRequestDao;
    private final LoansDao loansDao;
    private final LoanItemDao loanItemDao;

    @Transactional
    public void makeReservation(Cart cart, List<CartItem> cartItems) {
        Reservations reservation = Reservations.builder()
                .cart(cart)
                .reservationNumber(createReservationNumber())
                .reservationItem(makeReservationItemList(cartItems))
                .reservationMakeDate(LocalDateTime.now())
                .reservationMakeDate(LocalDateTime.now().plusDays(3L))
                .build();
        reservationsDao.saveReservations(reservation);
    }

    private String createReservationNumber() {
        SecureRandom random = new SecureRandom();
        StringBuilder employeeNumber = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            employeeNumber.append(digit);
        }
        return employeeNumber.toString();
    }

    private List<ReservationItem> makeReservationItemList(List<CartItem> cartItems) {
        List<ReservationItem> reservationItemList = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            ReservationItem reservationItem = ReservationItem.builder()
                    .title(cartItem.getTitle())
                    .book(cartItem.getBook())
                    .quantity(cartItem.getQuantity())
                    .build();

            reservationItemList.add(reservationItem);
        }

        return reservationItemList;

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

    public Reservations findByReservationNumber(String reservationNumber) {
        Optional<Reservations> reservation = reservationsDao.findByReservationNumber(reservationNumber);
        if (reservation.isEmpty()) {
            throw new NotFoundException("Could not find reservation: " + reservation);
        }
        return reservation.get();
    }

    @Transactional
    public void cancelReservation(Integer reservationId) {
        Reservations byId = findById(reservationId);
        String reservationNumber = byId.getReservationNumber();
//        loanItemDao.deleteByReservationId(reservationId);
//        loansDao.deleteByReservationId(reservationId);
//        loanRequestItemDao.deleteByReservationId(reservationId);
//        loanRequestDao.deleteByReservationId(reservationId);
        reservationItemDao.deleteByReservationNumber(reservationNumber);
        reservationsDao.deleteById(reservationId);
    }

    @Transactional
    public void deleteByReservationNumber(String reservationNumber) {
        Reservations reservation = findByReservationNumber(reservationNumber);
        reservationsHistoryDao.saveReservationsHistory(reservation);
        reservationItemDao.deleteByReservationNumber(reservationNumber);
        reservationsDao.deleteByReservationNumber(reservationNumber);
    }
}
