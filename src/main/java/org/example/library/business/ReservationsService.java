package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.*;
import org.example.library.domain.*;
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
    private final ReservationItemDao reservationItemDao;

    @Transactional
    public Reservations makeReservation(Cart cart) {


        Reservations reservation = Reservations.builder()
                .cart(cart)
                .reservationNumber(createReservationNumber())
                .reservationMakeDate(LocalDateTime.now())
                .reservationHoldToDate(LocalDateTime.now().plusDays(3))
                .build();

        Reservations reservations = reservationsDao.saveReservations(reservation);
        System.out.println("Reservation: " + reservations);
        return reservations;


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
    public void cancelReservation(String reservationNumber) {

        reservationItemDao.deleteByReservationNumber(reservationNumber);
        reservationsDao.deleteByReservationNumber(reservationNumber);
    }

    @Transactional
    public void deleteByReservationNumber(String reservationNumber) {
        reservationItemDao.deleteByReservationNumber(reservationNumber);
        reservationsDao.deleteByReservationNumber(reservationNumber);
    }
}
