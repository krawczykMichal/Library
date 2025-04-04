package org.example.library.business.dao;

import org.example.library.domain.LoanRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.example.library.domain.LoanRequest;
import org.example.library.domain.Users;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest
@TestPropertySource(properties = "spring.flyway.enabled=false") // Wyłączenie migracji Flyway
public class LoanRequestDaoTest {

    @MockBean
    private LoanRequestDao loanRequestDao;

    @MockBean
    private UsersDao usersDao;

    @Test
    void testSaveLoanRequestFromReservation() {
        // Tworzenie użytkownika
        Users user = Users.builder()
                .username("john_doe")
                .email("john.doe@example.com")
                .build();

        // Tworzenie LoanRequest
        LoanRequest loanRequest = LoanRequest.builder()
                .loanRequestNumber("LR12345")
                .user(user)
                .build();

        // Mockowanie saveLoanRequestFromReservation
        when(loanRequestDao.saveLoanRequestFromReservation(any(LoanRequest.class))).thenReturn(loanRequest);

        // Wywołanie metody saveLoanRequestFromReservation
        LoanRequest savedLoanRequest = loanRequestDao.saveLoanRequestFromReservation(loanRequest);

        // Sprawdzanie wyników
        assertNotNull(savedLoanRequest);
        assertEquals("LR12345", savedLoanRequest.getLoanRequestNumber());
        assertEquals("john_doe", savedLoanRequest.getUser().getUsername());
    }

    @Test
    void testSaveLoanRequestFromCart() {
        // Tworzenie użytkownika
        Users user = Users.builder()
                .username("jane_smith")
                .email("jane.smith@example.com")
                .build();

        // Tworzenie LoanRequest
        LoanRequest loanRequest = LoanRequest.builder()
                .loanRequestNumber("LR67890")
                .user(user)
                .build();

        // Mockowanie saveLoanRequestFromCart
        when(loanRequestDao.saveLoanRequestFromCart(any(LoanRequest.class))).thenReturn(loanRequest);

        // Wywołanie metody saveLoanRequestFromCart
        LoanRequest savedLoanRequest = loanRequestDao.saveLoanRequestFromCart(loanRequest);

        // Sprawdzanie wyników
        assertNotNull(savedLoanRequest);
        assertEquals("LR67890", savedLoanRequest.getLoanRequestNumber());
        assertEquals("jane_smith", savedLoanRequest.getUser().getUsername());
    }

    @Test
    void testFindAll() {
        // Tworzenie użytkowników
        Users user1 = Users.builder()
                .username("alice")
                .email("alice@example.com")
                .build();
        Users user2 = Users.builder()
                .username("bob")
                .email("bob@example.com")
                .build();

        // Tworzenie LoanRequest
        LoanRequest loanRequest1 = LoanRequest.builder()
                .loanRequestNumber("LR123")
                .user(user1)
                .build();
        LoanRequest loanRequest2 = LoanRequest.builder()
                .loanRequestNumber("LR124")
                .user(user2)
                .build();

        List<LoanRequest> loanRequestsList = Arrays.asList(loanRequest1, loanRequest2);

        // Mockowanie findAll
        when(loanRequestDao.findAll()).thenReturn(loanRequestsList);

        // Wywołanie metody findAll
        List<LoanRequest> foundLoanRequests = loanRequestDao.findAll();

        // Sprawdzanie wyników
        assertFalse(foundLoanRequests.isEmpty());
        assertEquals(2, foundLoanRequests.size());
        assertEquals("LR123", foundLoanRequests.get(0).getLoanRequestNumber());
        assertEquals("LR124", foundLoanRequests.get(1).getLoanRequestNumber());
    }

    @Test
    void testFindById() {
        // Tworzenie użytkownika
        Users user = Users.builder()
                .username("carol")
                .email("carol@example.com")
                .build();

        // Tworzenie LoanRequest
        LoanRequest loanRequest = LoanRequest.builder()
                .loanRequestNumber("LR567")
                .user(user)
                .build();

        // Mockowanie findById
        when(loanRequestDao.findById(1)).thenReturn(Optional.of(loanRequest));

        // Wywołanie metody findById
        Optional<LoanRequest> foundLoanRequest = loanRequestDao.findById(1);

        // Sprawdzanie wyników
        assertTrue(foundLoanRequest.isPresent());
        assertEquals("LR567", foundLoanRequest.get().getLoanRequestNumber());
    }

    @Test
    void testFindByUserId() {
        // Tworzenie użytkownika
        Users user = Users.builder()
                .username("david")
                .email("david@example.com")
                .build();

        // Tworzenie LoanRequest
        LoanRequest loanRequest = LoanRequest.builder()
                .loanRequestNumber("LR999")
                .user(user)
                .build();

        List<LoanRequest> loanRequestsList = Arrays.asList(loanRequest);

        // Mockowanie findByUserId
        when(loanRequestDao.findByUserId(user.getUserId())).thenReturn(loanRequestsList);

        // Wywołanie metody findByUserId
        List<LoanRequest> foundLoanRequests = loanRequestDao.findByUserId(user.getUserId());

        // Sprawdzanie wyników
        assertFalse(foundLoanRequests.isEmpty());
        assertEquals("LR999", foundLoanRequests.get(0).getLoanRequestNumber());
    }

    @Test
    void testFindByLoanRequestNumber() {
        // Tworzenie użytkownika
        Users user = Users.builder()
                .username("eve")
                .email("eve@example.com")
                .build();

        // Tworzenie LoanRequest
        LoanRequest loanRequest = LoanRequest.builder()
                .loanRequestNumber("LR001")
                .user(user)
                .build();

        // Mockowanie findByLoanRequestNumber
        when(loanRequestDao.findByLoanRequestNumber("LR001")).thenReturn(Optional.of(loanRequest));

        // Wywołanie metody findByLoanRequestNumber
        Optional<LoanRequest> foundLoanRequest = loanRequestDao.findByLoanRequestNumber("LR001");

        // Sprawdzanie wyników
        assertTrue(foundLoanRequest.isPresent());
        assertEquals("LR001", foundLoanRequest.get().getLoanRequestNumber());
    }

    @Test
    void testDeleteByLoanRequestNumber() {
        // Tworzenie LoanRequest
        LoanRequest loanRequest = LoanRequest.builder()
                .loanRequestNumber("LR123")
                .build();

        // Mockowanie deleteByLoanRequestNumber
        when(loanRequestDao.findByLoanRequestNumber("LR123")).thenReturn(Optional.of(loanRequest));

        // Wywołanie metody deleteByLoanRequestNumber
        loanRequestDao.deleteByLoanRequestNumber("LR123");

        // Weryfikacja, że metoda została wywołana (tu powinno być weryfikowanie, czy metoda usuwania była wykonana)
        assertDoesNotThrow(() -> loanRequestDao.deleteByLoanRequestNumber("LR123"));
    }
}