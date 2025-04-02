package org.example.library.business;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.example.library.business.dao.BooksDao;
import org.example.library.business.dao.ReservationItemDao;
import org.example.library.business.dao.ReservationsDao;
import org.example.library.domain.Books;
import org.example.library.domain.CartItem;
import org.example.library.domain.ReservationItem;
import org.example.library.domain.Reservations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ReservationItemServiceTest {

    @Mock
    private ReservationItemDao reservationItemDao;

    @Mock
    private BooksDao booksDao;

    @Mock
    private ReservationsDao reservationsDao;

    @InjectMocks
    private ReservationItemService reservationItemService;

    private Reservations reservation;
    private CartItem cartItem;
    private Books book;

    @BeforeEach
    void setUp() {
        book = Books.builder().copies(5).build();

        cartItem = CartItem.builder()
                .title("Test Book")
                .book(book)
                .quantity(2)
                .build();

        reservation = Reservations.builder()
                .reservationNumber("RES123")
                .build();
    }

    @Test
    void testSaveReservationItem() {
        when(reservationsDao.findByReservationNumber("RES123")).thenReturn(Optional.of(reservation));

        reservationItemService.saveReservationItem(reservation, List.of(cartItem));

        verify(reservationsDao, times(1)).findByReservationNumber("RES123");
        verify(reservationItemDao, times(1)).save(any(ReservationItem.class));
        verify(booksDao, times(1)).saveBook(any(Books.class));
    }
}
