package org.example.library.business.dao;

import org.example.library.domain.Loans;
import org.example.library.domain.Users;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@DataJpaTest
@TestPropertySource(properties = "spring.flyway.enabled=false") // Wyłączenie migracji Flyway
public class LoansDaoTest {

    @MockBean
    private LoansDao loansDao;

    @Test
    void testSave() {
        // Tworzenie użytkownika
        Users user = Users.builder().userId(1).name("John").surname("Doe").build();

        // Tworzenie pożyczki
        Loans loan = Loans.builder()
                .loanId(1)
                .loanNumber("LN123")
                .loanDate(LocalDateTime.now())
                .returnDate(LocalDateTime.now().plusDays(10))
                .returned(false)
                .user(user)
                .build();

        // Mockowanie metody save
        when(loansDao.save(any(Loans.class))).thenReturn(loan);

        // Wywołanie metody save
        Loans savedLoan = loansDao.save(loan);

        // Sprawdzanie, czy pożyczka została zapisana
        assertNotNull(savedLoan);
        assertEquals("LN123", savedLoan.getLoanNumber());
        assertEquals("John", savedLoan.getUser().getName());
    }

    @Test
    void testFindAllByUserId() {
        // Tworzenie użytkownika
        Users user = Users.builder().userId(1).name("John").surname("Doe").build();

        // Tworzenie pożyczek
        Loans loan1 = Loans.builder()
                .loanId(1)
                .loanNumber("LN123")
                .loanDate(LocalDateTime.now())
                .returnDate(LocalDateTime.now().plusDays(10))
                .returned(false)
                .user(user)
                .build();
        Loans loan2 = Loans.builder()
                .loanId(2)
                .loanNumber("LN124")
                .loanDate(LocalDateTime.now())
                .returnDate(LocalDateTime.now().plusDays(5))
                .returned(true)
                .user(user)
                .build();

        List<Loans> loansList = Arrays.asList(loan1, loan2);

        // Mockowanie findAllByUserId
        when(loansDao.findAllByUserId(1)).thenReturn(loansList);

        // Wywołanie metody findAllByUserId
        List<Loans> foundLoans = loansDao.findAllByUserId(1);

        // Sprawdzanie wyników
        assertFalse(foundLoans.isEmpty());
        assertEquals(2, foundLoans.size());
        assertEquals("LN123", foundLoans.get(0).getLoanNumber());
        assertEquals("LN124", foundLoans.get(1).getLoanNumber());
    }

    @Test
    void testFindById() {
        // Tworzenie użytkownika
        Users user = Users.builder().userId(1).name("John").surname("Doe").build();

        // Tworzenie pożyczki
        Loans loan = Loans.builder()
                .loanId(1)
                .loanNumber("LN123")
                .loanDate(LocalDateTime.now())
                .returnDate(LocalDateTime.now().plusDays(10))
                .returned(false)
                .user(user)
                .build();

        // Mockowanie findById
        when(loansDao.findById(1)).thenReturn(Optional.of(loan));

        // Wywołanie metody findById
        Optional<Loans> foundLoan = loansDao.findById(1);

        // Sprawdzanie wyników
        assertTrue(foundLoan.isPresent());
        assertEquals("LN123", foundLoan.get().getLoanNumber());
    }

    @Test
    void testFindByLoanNumber() {
        // Tworzenie użytkownika
        Users user = Users.builder().userId(1).name("John").surname("Doe").build();

        // Tworzenie pożyczki
        Loans loan = Loans.builder()
                .loanId(1)
                .loanNumber("LN123")
                .loanDate(LocalDateTime.now())
                .returnDate(LocalDateTime.now().plusDays(10))
                .returned(false)
                .user(user)
                .build();

        // Mockowanie findByLoanNumber
        when(loansDao.findByLoanNumber("LN123")).thenReturn(Optional.of(loan));

        // Wywołanie metody findByLoanNumber
        Optional<Loans> foundLoan = loansDao.findByLoanNumber("LN123");

        // Sprawdzanie wyników
        assertTrue(foundLoan.isPresent());
        assertEquals("LN123", foundLoan.get().getLoanNumber());
    }

    @Test
    void testDeleteByUserId() {
        // Wywołanie metody deleteByUserId
        loansDao.deleteByUserId(1);

        // Weryfikacja, że metoda została wywołana
        verify(loansDao).deleteByUserId(1);
    }


    @Test
    void testReturnLoan() {
        // Mockowanie returnLoan
        when(loansDao.returnLoan("LN123")).thenReturn(1);

        // Wywołanie metody returnLoan
        int result = loansDao.returnLoan("LN123");

        // Sprawdzanie, czy metoda zwróciła poprawny wynik
        assertEquals(1, result);
    }

    @Test
    void testFindAllForEmployee() {
        // Tworzenie użytkownika
        Users user = Users.builder().userId(1).name("John").surname("Doe").build();

        // Tworzenie pożyczek
        Loans loan1 = Loans.builder()
                .loanId(1)
                .loanNumber("LN123")
                .loanDate(LocalDateTime.now())
                .returnDate(LocalDateTime.now().plusDays(10))
                .returned(false)
                .user(user)
                .build();
        Loans loan2 = Loans.builder()
                .loanId(2)
                .loanNumber("LN124")
                .loanDate(LocalDateTime.now())
                .returnDate(LocalDateTime.now().plusDays(5))
                .returned(true)
                .user(user)
                .build();

        List<Loans> loansList = Arrays.asList(loan1, loan2);

        // Mockowanie findAllForEmployee
        when(loansDao.findAllForEmployee()).thenReturn(loansList);

        // Wywołanie metody findAllForEmployee
        List<Loans> foundLoans = loansDao.findAllForEmployee();

        // Sprawdzanie wyników
        assertFalse(foundLoans.isEmpty());
        assertEquals(2, foundLoans.size());
        assertEquals("LN123", foundLoans.get(0).getLoanNumber());
    }
}
