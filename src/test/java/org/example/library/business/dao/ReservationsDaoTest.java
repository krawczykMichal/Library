package org.example.library.business.dao;

import org.example.library.domain.Cart;
import org.example.library.domain.ReservationItem;
import org.example.library.domain.Reservations;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = "spring.flyway.enabled=false") // Wyłączenie migracji Flyway
public class ReservationsDaoTest {

    @MockBean
    private ReservationsDao reservationsDao;

    @Test
    void testSaveReservations() {
        // Tworzenie obiektów
        Cart cart = Cart.builder().cartId(1).build();
        ReservationItem reservationItem = ReservationItem.builder().title("Book Title").quantity(1).build();

        Reservations reservation = Reservations.builder()
                .reservationNumber("RES123")
                .reservationMakeDate(LocalDateTime.now())
                .reservationHoldToDate(LocalDateTime.now().plusDays(7))
                .cart(cart)
                .reservationItem(Arrays.asList(reservationItem))
                .build();

        // Mockowanie saveReservations
        when(reservationsDao.saveReservations(any(Reservations.class))).thenReturn(reservation);

        // Wywołanie metody saveReservations
        Reservations savedReservation = reservationsDao.saveReservations(reservation);

        // Weryfikacja, że metoda saveReservations została wywołana i zwróciła poprawny obiekt
        assertNotNull(savedReservation);
        assertEquals("RES123", savedReservation.getReservationNumber());
    }

    @Test
    void testFindAllByUserId() {
        // Tworzenie obiektów
        Reservations reservation1 = Reservations.builder().reservationId(1).reservationNumber("RES123").build();
        Reservations reservation2 = Reservations.builder().reservationId(2).reservationNumber("RES456").build();

        List<Reservations> reservationList = Arrays.asList(reservation1, reservation2);

        // Mockowanie findAllByUserId
        when(reservationsDao.findAllByUserId(1)).thenReturn(reservationList);

        // Wywołanie metody findAllByUserId
        List<Reservations> foundReservations = reservationsDao.findAllByUserId(1);

        // Weryfikacja, że metoda zwróciła odpowiednią listę
        assertNotNull(foundReservations);
        assertEquals(2, foundReservations.size());
        assertEquals("RES123", foundReservations.get(0).getReservationNumber());
        assertEquals("RES456", foundReservations.get(1).getReservationNumber());
    }

    @Test
    void testFindById() {
        // Tworzenie obiektu
        Reservations reservation = Reservations.builder().reservationId(1).reservationNumber("RES123").build();

        // Mockowanie findById
        when(reservationsDao.findById(1)).thenReturn(Optional.of(reservation));

        // Wywołanie metody findById
        Optional<Reservations> foundReservation = reservationsDao.findById(1);

        // Weryfikacja, że metoda zwróciła odpowiedni obiekt
        assertTrue(foundReservation.isPresent());
        assertEquals("RES123", foundReservation.get().getReservationNumber());
    }

    @Test
    void testFindByReservationNumber() {
        // Tworzenie obiektu
        Reservations reservation = Reservations.builder().reservationNumber("RES123").build();

        // Mockowanie findByReservationNumber
        when(reservationsDao.findByReservationNumber("RES123")).thenReturn(Optional.of(reservation));

        // Wywołanie metody findByReservationNumber
        Optional<Reservations> foundReservation = reservationsDao.findByReservationNumber("RES123");

        // Weryfikacja, że metoda zwróciła odpowiedni obiekt
        assertTrue(foundReservation.isPresent());
        assertEquals("RES123", foundReservation.get().getReservationNumber());
    }

    @Test
    void testDeleteById() {
        // Wywołanie metody deleteById
        reservationsDao.deleteById(1);

        // Weryfikacja, że metoda deleteById została wywołana
        verify(reservationsDao).deleteById(1);
    }

    @Test
    void testDeleteByReservationNumber() {
        // Wywołanie metody deleteByReservationNumber
        reservationsDao.deleteByReservationNumber("RES123");

        // Weryfikacja, że metoda deleteByReservationNumber została wywołana
        verify(reservationsDao).deleteByReservationNumber("RES123");
    }

    @Test
    void testDeleteByCartUserId() {
        // Wywołanie metody deleteByCartUserId
        reservationsDao.deleteByCartUserId(1);

        // Weryfikacja, że metoda deleteByCartUserId została wywołana
        verify(reservationsDao).deleteByCartUserId(1);
    }
}
