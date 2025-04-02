package org.example.library.business;

import org.example.library.business.dao.LoanRequestDao;
import org.example.library.business.dao.LoanRequestItemDao;
import org.example.library.domain.*;
import org.example.library.domain.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanRequestServiceTest {

    @Mock
    private LoanRequestDao loanRequestDao;

    @Mock
    private LoanRequestItemDao loanRequestItemDao;

    @Mock
    private LoanRequestItemService loanRequestItemService;

    @InjectMocks
    private LoanRequestService loanRequestService;

    // Przykładowe obiekty do testów
    private Reservations reservation;
    private ReservationItem reservationItem1;
    private ReservationItem reservationItem2;
    private Users user;
    private Cart cart;
    private CartItem cartItem1;
    private CartItem cartItem2;
    private LoanRequest existingLoanRequest;

    @BeforeEach
    void setUp() {
        // Ustawiamy przykładową rezerwację z dwoma pozycjami
        reservationItem1 = ReservationItem.builder()
                .title("Book A")
                .book(Books.builder().title("Book A").build())
                .quantity(1)
                .build();

        reservationItem2 = ReservationItem.builder()
                .title("Book B")
                .book(Books.builder().title("Book B").build())
                .quantity(2)
                .build();

        List<ReservationItem> reservationItems = List.of(reservationItem1, reservationItem2);

        reservation = Reservations.builder()
                .reservationNumber("RES123")
                .reservationItem(reservationItems)
                .build();

        // Ustawiamy przykładowego użytkownika
        user = Users.builder()
                .userId(1)
                .username("testuser")
                .build();

        // Przykładowy LoanRequest – numer rezerwacji jako numer żądania
        existingLoanRequest = LoanRequest.builder()
                .loanRequestNumber("RES123")
                .requestDate(LocalDateTime.now())
                .build()
                .withUser(user);

        // Ustawiamy przykładowy koszyk z dwoma pozycjami
        cart = Cart.builder()
                .cartId(1)
                .user(user)
                .build();

        cartItem1 = CartItem.builder()
                .title("Book C")
                .book(Books.builder().title("Book C").build())
                .quantity(1)
                .build();

        cartItem2 = CartItem.builder()
                .title("Book D")
                .book(Books.builder().title("Book D").build())
                .quantity(3)
                .build();
    }

    @Test
    void testMakeLoanRequestFromReservation() {
        // Stubujemy wywołanie zapisu żądania na podstawie rezerwacji, zwracając existingLoanRequest
        when(loanRequestDao.saveLoanRequestFromReservation(any(LoanRequest.class)))
                .thenReturn(existingLoanRequest);

        // Wywołanie metody – powinno utworzyć LoanRequest z reservationNumber i użytkownikiem
        loanRequestService.makeLoanRequestFromReservation(reservation, user);

        // Weryfikujemy, że metoda saveLoanRequestFromReservation została wywołana z poprawnym LoanRequest
        ArgumentCaptor<LoanRequest> loanRequestCaptor = ArgumentCaptor.forClass(LoanRequest.class);
        verify(loanRequestDao, times(1)).saveLoanRequestFromReservation(loanRequestCaptor.capture());
        LoanRequest capturedRequest = loanRequestCaptor.getValue();
        assertEquals("RES123", capturedRequest.getLoanRequestNumber());
        // Użytkownik powinien być ustawiony przez withUser
        assertEquals(user, capturedRequest.getUser());

        // Weryfikujemy, że po zapisie wywołano metodę addItems na serwisie LoanRequestItemService
        verify(loanRequestItemService, times(1)).addItems(reservation.getReservationItem(), existingLoanRequest);
    }

    @Test
    void testFindAll() {
        List<LoanRequest> expectedList = new ArrayList<>();
        expectedList.add(existingLoanRequest);
        when(loanRequestDao.findAll()).thenReturn(expectedList);

        List<LoanRequest> actualList = loanRequestService.findAll();

        assertEquals(expectedList, actualList);
        verify(loanRequestDao, times(1)).findAll();
    }

    @Test
    void testFindByIdFound() {
        when(loanRequestDao.findById(1)).thenReturn(Optional.of(existingLoanRequest));

        LoanRequest found = loanRequestService.findById(1);

        assertNotNull(found);
        verify(loanRequestDao, times(1)).findById(1);
    }

    @Test
    void testFindByIdNotFound() {
        when(loanRequestDao.findById(2)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> loanRequestService.findById(2));

        assertTrue(exception.getMessage().contains("Could not find loan request with id: 2"));
    }

    @Test
    void testMakeLoanRequestFromCart() {
        // Przygotowanie listy pozycji z koszyka
        List<CartItem> cartItems = List.of(cartItem1, cartItem2);

        // Wywołanie metody – sprawdzamy, czy DAO zapisze LoanRequest
        loanRequestService.makeLoanRequestFromCart(cart, cartItems);

        // Weryfikujemy, że DAO otrzymało LoanRequest z poprawnie przekonwertowanymi pozycjami
        ArgumentCaptor<LoanRequest> captor = ArgumentCaptor.forClass(LoanRequest.class);
        verify(loanRequestDao, times(1)).saveLoanRequestFromCart(captor.capture());
        LoanRequest savedRequest = captor.getValue();

        // Weryfikujemy, że lista pozycji została utworzona na podstawie koszykowych pozycji
        List<LoanRequestItem> items = savedRequest.getLoanRequestItems();
        assertNotNull(items);
        assertEquals(2, items.size());

        // Sprawdzamy po kolei, czy dane z CartItem zostały poprawnie przeniesione
        LoanRequestItem item1 = items.get(0);
        assertEquals(cartItem1.getTitle(), item1.getTitle());
        assertEquals(cartItem1.getBook(), item1.getBook());
        assertEquals(cartItem1.getQuantity(), item1.getQuantity());

        LoanRequestItem item2 = items.get(1);
        assertEquals(cartItem2.getTitle(), item2.getTitle());
        assertEquals(cartItem2.getBook(), item2.getBook());
        assertEquals(cartItem2.getQuantity(), item2.getQuantity());

        // Użytkownik z koszyka powinien być ustawiony
        assertEquals(user, savedRequest.getUser());
    }

    @Test
    void testFindByUserId() {
        List<LoanRequest> expectedList = List.of(existingLoanRequest);
        when(loanRequestDao.findByUserId(user.getUserId())).thenReturn(expectedList);

        List<LoanRequest> actualList = loanRequestService.findByUserId(user.getUserId());
        assertEquals(expectedList, actualList);
        verify(loanRequestDao, times(1)).findByUserId(user.getUserId());
    }

    @Test
    void testFindByLoanRequestNumberFound() {
        when(loanRequestDao.findByLoanRequestNumber("LR001")).thenReturn(Optional.of(existingLoanRequest));

        LoanRequest found = loanRequestService.findByLoanRequestNumber("LR001");

        assertNotNull(found);
        verify(loanRequestDao, times(1)).findByLoanRequestNumber("LR001");
    }

    @Test
    void testFindByLoanRequestNumberNotFound() {
        when(loanRequestDao.findByLoanRequestNumber("LR002")).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> loanRequestService.findByLoanRequestNumber("LR002"));

        assertTrue(exception.getMessage().contains("Could not find loan request with loanRequestNumber: LR002"));
    }

    @Test
    void testDeleteLoanRequest() {
        // Przygotowujemy obiekt LoanRequest z identyfikatorem
        LoanRequest loanRequestToDelete = LoanRequest.builder()
                .loanRequestId(10)
                .loanRequestNumber("LR010")
                .build();

        doNothing().when(loanRequestItemDao).deleteByLoanRequestId(10);
        doNothing().when(loanRequestDao).deleteByLoanRequestNumber("LR010");

        loanRequestService.deleteLoanRequest(loanRequestToDelete);

        verify(loanRequestItemDao, times(1)).deleteByLoanRequestId(10);
        verify(loanRequestDao, times(1)).deleteByLoanRequestNumber("LR010");
    }
}