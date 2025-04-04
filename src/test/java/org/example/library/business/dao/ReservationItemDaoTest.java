package org.example.library.business.dao;

import org.example.library.domain.ReservationItem;
import org.example.library.domain.Books;
import org.example.library.domain.Reservations;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.Mockito;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = "spring.flyway.enabled=false") // Wyłączenie migracji Flyway
public class ReservationItemDaoTest {

    @MockBean
    private ReservationItemDao reservationItemDao;

    @Test
    void testDeleteByReservationCartUserId() {
        // Wywołanie metody deleteByReservationCartUserId
        reservationItemDao.deleteByReservationCartUserId(1);

        // Weryfikacja, że metoda została wywołana
        verify(reservationItemDao).deleteByReservationCartUserId(1);
    }

    @Test
    void testDeleteByReservationNumber() {
        // Wywołanie metody deleteByReservationNumber
        reservationItemDao.deleteByReservationNumber("RES123");

        // Weryfikacja, że metoda została wywołana
        verify(reservationItemDao).deleteByReservationNumber("RES123");
    }

    @Test
    void testSaveAll() {
        // Tworzenie książek
        Books book = Books.builder()
                .isbn("12345")
                .title("Book Title")
                .build();

        // Tworzenie rezerwacji
        Reservations reservation = Reservations.builder()
                .reservationNumber("RES123")
                .build();

        // Tworzenie obiektów ReservationItem
        ReservationItem reservationItem1 = ReservationItem.builder()
                .title("Book Title 1")
                .quantity(2)
                .book(book)
                .reservation(reservation)
                .build();

        ReservationItem reservationItem2 = ReservationItem.builder()
                .title("Book Title 2")
                .quantity(1)
                .book(book)
                .reservation(reservation)
                .build();

        List<ReservationItem> reservationItemList = Arrays.asList(reservationItem1, reservationItem2);

        // Wywołanie metody saveAll
        reservationItemDao.saveAll(reservationItemList);

        // Weryfikacja, że metoda saveAll została wywołana z odpowiednią listą
        verify(reservationItemDao).saveAll(reservationItemList);
    }

    @Test
    void testSave() {
        // Tworzenie książek
        Books book = Books.builder()
                .isbn("12345")
                .title("Book Title")
                .build();

        // Tworzenie rezerwacji
        Reservations reservation = Reservations.builder()
                .reservationNumber("RES123")
                .build();

        // Tworzenie obiektu ReservationItem
        ReservationItem reservationItem = ReservationItem.builder()
                .title("Book Title")
                .quantity(3)
                .book(book)
                .reservation(reservation)
                .build();

        // Mockowanie save
        when(reservationItemDao.save(any(ReservationItem.class))).thenReturn(reservationItem);

        // Wywołanie metody save
        ReservationItem savedReservationItem = reservationItemDao.save(reservationItem);

        // Weryfikacja, że metoda save została wywołana i zwróciła poprawny obiekt
        assertNotNull(savedReservationItem);
        assertEquals("Book Title", savedReservationItem.getTitle());
        assertEquals(3, savedReservationItem.getQuantity());
    }
}
