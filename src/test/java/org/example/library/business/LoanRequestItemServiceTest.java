package org.example.library.business;
import org.example.library.business.dao.LoanRequestItemDao;
import org.example.library.domain.Books;
import org.example.library.domain.LoanRequest;
import org.example.library.domain.LoanRequestItem;
import org.example.library.domain.ReservationItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanRequestItemServiceTest {

    @Mock
    private LoanRequestItemDao loanRequestItemDao;

    @InjectMocks
    private LoanRequestItemService loanRequestItemService;

    private LoanRequest loanRequest;
    private ReservationItem reservationItem1;
    private ReservationItem reservationItem2;

    @BeforeEach
    void setUp() {
        // Tworzymy przykładowy LoanRequest przy użyciu buildera
        loanRequest = LoanRequest.builder()
                .loanRequestNumber("LR123")
                .build();

        // Tworzymy przykładowe ReservationItemy przy użyciu buildera
        reservationItem1 = ReservationItem.builder()
                .title("Book 1")
                .book(Books.builder().title("Book 1").build())
                .quantity(1)
                .build();

        reservationItem2 = ReservationItem.builder()
                .title("Book 2")
                .book(Books.builder().title("Book 2").build())
                .quantity(2)
                .build();
    }

    @Test
    void testFindByLoanRequestNumber() {
        String loanRequestNumber = "LR123";
        List<LoanRequestItem> expectedItems = Arrays.asList(
                LoanRequestItem.builder().title("Book 1").build(),
                LoanRequestItem.builder().title("Book 2").build()
        );
        when(loanRequestItemDao.findByLoanRequestNumber(loanRequestNumber)).thenReturn(expectedItems);

        List<LoanRequestItem> actualItems = loanRequestItemService.findByLoanRequestNumber(loanRequestNumber);

        assertEquals(expectedItems, actualItems);
        verify(loanRequestItemDao, times(1)).findByLoanRequestNumber(loanRequestNumber);
    }

    @Test
    void testAddItems() {
        List<ReservationItem> reservationItems = Arrays.asList(reservationItem1, reservationItem2);

        // Wywołanie metody addItems, która dla każdego ReservationItem tworzy LoanRequestItem i zapisuje go przy użyciu DAO.
        loanRequestItemService.addItems(reservationItems, loanRequest);

        // Używamy ArgumentCaptor do przechwycenia obiektów przekazywanych do metody save.
        ArgumentCaptor<LoanRequestItem> captor = ArgumentCaptor.forClass(LoanRequestItem.class);
        verify(loanRequestItemDao, times(2)).save(captor.capture());
        List<LoanRequestItem> savedItems = captor.getAllValues();

        // Weryfikujemy, czy pierwszy zapisany obiekt odpowiada reservationItem1
        LoanRequestItem item1 = savedItems.get(0);
        assertEquals(reservationItem1.getTitle(), item1.getTitle());
        assertEquals(reservationItem1.getBook(), item1.getBook());
        assertEquals(reservationItem1.getQuantity(), item1.getQuantity());
        assertEquals(loanRequest, item1.getLoanRequest());

        // Weryfikujemy, czy drugi zapisany obiekt odpowiada reservationItem2
        LoanRequestItem item2 = savedItems.get(1);
        assertEquals(reservationItem2.getTitle(), item2.getTitle());
        assertEquals(reservationItem2.getBook(), item2.getBook());
        assertEquals(reservationItem2.getQuantity(), item2.getQuantity());
        assertEquals(loanRequest, item2.getLoanRequest());
    }
}
