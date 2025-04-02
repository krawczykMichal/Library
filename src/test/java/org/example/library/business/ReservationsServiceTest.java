package org.example.library.business;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.example.library.business.dao.ReservationItemDao;
import org.example.library.business.dao.ReservationsDao;
import org.example.library.domain.Cart;
import org.example.library.domain.Reservations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ReservationsServiceTest {

    @Mock
    private ReservationsDao reservationsDao;

    @Mock
    private ReservationItemDao reservationItemDao;

    @InjectMocks
    private ReservationsService reservationsService;

    private Reservations reservation;
    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = Cart.builder().build();

        reservation = Reservations.builder()
                .reservationNumber("RES123")
                .cart(cart)
                .reservationMakeDate(LocalDateTime.now())
                .reservationHoldToDate(LocalDateTime.now().plusDays(3))
                .build();
    }

    @Test
    void testMakeReservation() {
        when(reservationsDao.saveReservations(any(Reservations.class))).thenReturn(reservation);

        Reservations result = reservationsService.makeReservation(cart);

        assertNotNull(result);
        assertEquals("RES123", result.getReservationNumber());
        verify(reservationsDao, times(1)).saveReservations(any(Reservations.class));
    }

    @Test
    void testFindAllByUserId() {
        when(reservationsDao.findAllByUserId(1)).thenReturn(List.of(reservation));

        List<Reservations> result = reservationsService.findAllByUserId(1);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(reservationsDao, times(1)).findAllByUserId(1);
    }

    @Test
    void testFindById() {
        when(reservationsDao.findById(1)).thenReturn(Optional.of(reservation));

        Reservations result = reservationsService.findById(1);

        assertNotNull(result);
        assertEquals("RES123", result.getReservationNumber());
        verify(reservationsDao, times(1)).findById(1);
    }

    @Test
    void testFindByReservationNumber() {
        when(reservationsDao.findByReservationNumber("RES123")).thenReturn(Optional.of(reservation));

        Reservations result = reservationsService.findByReservationNumber("RES123");

        assertNotNull(result);
        assertEquals("RES123", result.getReservationNumber());
        verify(reservationsDao, times(1)).findByReservationNumber("RES123");
    }

    @Test
    void testCancelReservation() {
        doNothing().when(reservationItemDao).deleteByReservationNumber("RES123");
        doNothing().when(reservationsDao).deleteByReservationNumber("RES123");

        reservationsService.cancelReservation("RES123");

        verify(reservationItemDao, times(1)).deleteByReservationNumber("RES123");
        verify(reservationsDao, times(1)).deleteByReservationNumber("RES123");
    }
}
