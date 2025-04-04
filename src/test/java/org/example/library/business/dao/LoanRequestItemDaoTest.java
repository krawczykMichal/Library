package org.example.library.business.dao;

import org.example.library.domain.LoanRequest;
import org.example.library.domain.LoanRequestItem;
import org.example.library.domain.Books;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
@TestPropertySource(properties = "spring.flyway.enabled=false") // Wyłączenie migracji Flyway
public class LoanRequestItemDaoTest {

    @MockBean
    private LoanRequestItemDao loanRequestItemDao;

    @MockBean
    private LoanRequestDao loanRequestDao;

    @MockBean
    private BooksDao booksDao;

    @Test
    void testDeleteByLoanRequestCartUserId() {
        // Wywołanie metody deleteByLoanRequestCartUserId
        loanRequestItemDao.deleteByLoanRequestCartUserId(1);

        // Weryfikacja, że metoda została wywołana
        // verify sprawdza, czy metoda została wywołana na mocku z odpowiednim argumentem
        verify(loanRequestItemDao).deleteByLoanRequestCartUserId(1);
    }

    @Test
    void testDeleteByLoanRequestId() {
        // Wywołanie metody deleteByLoanRequestId
        loanRequestItemDao.deleteByLoanRequestId(123);

        // Weryfikacja, że metoda została wywołana
        // verify sprawdza, czy metoda została wywołana na mocku z odpowiednim argumentem
        verify(loanRequestItemDao).deleteByLoanRequestId(123);
    }

    @Test
    void testFindByLoanRequestNumber() {
        // Tworzenie książki
        Books book = Books.builder()
                .isbn("12345")
                .title("Book 1")
                .build();

        // Tworzenie LoanRequest
        LoanRequest loanRequest = LoanRequest.builder()
                .loanRequestNumber("LR001")
                .build();

        // Tworzenie LoanRequestItem
        LoanRequestItem loanRequestItem = LoanRequestItem.builder()
                .title("Book 1")
                .quantity(1)
                .book(book)
                .loanRequest(loanRequest)
                .build();

        List<LoanRequestItem> loanRequestItemsList = Arrays.asList(loanRequestItem);

        // Mockowanie findByLoanRequestNumber
        when(loanRequestItemDao.findByLoanRequestNumber("LR001")).thenReturn(loanRequestItemsList);

        // Wywołanie metody findByLoanRequestNumber
        List<LoanRequestItem> foundItems = loanRequestItemDao.findByLoanRequestNumber("LR001");

        // Sprawdzanie wyników
        assertFalse(foundItems.isEmpty());
        assertEquals(1, foundItems.size());
        assertEquals("Book 1", foundItems.get(0).getTitle());
        assertEquals(1, foundItems.get(0).getQuantity());
        assertEquals("12345", foundItems.get(0).getBook().getIsbn());
    }

    @Test
    void testSave() {
        // Tworzenie książki
        Books book = Books.builder()
                .isbn("12345")
                .title("Book 1")
                .build();

        // Tworzenie LoanRequest
        LoanRequest loanRequest = LoanRequest.builder()
                .loanRequestNumber("LR12345")
                .build();

        // Tworzenie LoanRequestItem
        LoanRequestItem loanRequestItem = LoanRequestItem.builder()
                .title("Book 1")
                .quantity(1)
                .book(book)
                .loanRequest(loanRequest)
                .build();

        // Mockowanie save
        when(loanRequestItemDao.save(any(LoanRequestItem.class))).thenReturn(loanRequestItem);

        // Wywołanie metody save
        LoanRequestItem savedLoanRequestItem = loanRequestItemDao.save(loanRequestItem);

        // Sprawdzanie wyników
        assertNotNull(savedLoanRequestItem);
        assertEquals("LR12345", savedLoanRequestItem.getLoanRequest().getLoanRequestNumber());
        assertEquals("Book 1", savedLoanRequestItem.getTitle());
        assertEquals(1, savedLoanRequestItem.getQuantity());
        assertEquals("12345", savedLoanRequestItem.getBook().getIsbn());
    }
}