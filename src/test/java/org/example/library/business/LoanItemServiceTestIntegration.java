package org.example.library.business;

import jakarta.transaction.Transactional;
import org.example.library.business.dao.LoanItemDao;
import org.example.library.business.dao.LoansDao;
import org.example.library.business.dao.UsersDao;
import org.example.library.domain.*;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class LoanItemServiceTestIntegration {

    @Autowired
    private LoanItemService loanItemService;

    @Autowired
    private LoanItemDao loanItemDao;

    @Autowired
    private LoansDao loanDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private Flyway flyway;

    private Loans sampleLoan;
    private LoanItem sampleLoanItem;

    @BeforeEach
    void setUp() {
        // Czyszczenie bazy danych i ponowna migracja
        flyway.clean();
        flyway.migrate();

        Authors sampleAuthor = Authors.builder()
                .name("author")
                .surname("name")
                .authorCode("123456789")
                .build();

        // Tworzenie przykładowej książki
        Books sampleBook = Books.builder()
                .title("Sample Book")
                .author(sampleAuthor)
                .build();

        Users sampleUser = Users.builder()
                .name("Joe")
                .surname("Doe")
                .username("joe_doe")
                .email("Jdoe@Email.com")
                .phoneNumber("123456789")
                .build();
        Users user = usersDao.saveUser(sampleUser);


        // Tworzenie przykładowego LoanItem
        sampleLoanItem = LoanItem.builder()
                .title("Sample Loan Item")
                .quantity(1)
                .book(sampleBook)
                .build();

        // Tworzenie przykładowego Loan, do którego przypisany jest LoanItem
        sampleLoan = Loans.builder()
                .loanNumber("LN123456")
                .user(user)
                .loanDate(LocalDateTime.of(2024, 10, 10, 10, 0, 0))
                .loanItem(List.of(sampleLoanItem)) // Przypisanie LoanItem do Loan
                .build();

        // Zapis Loan w bazie danych (LoanItem zapisze się automatycznie)
        loanDao.save(sampleLoan);
    }

    @Test
    void testFindAllByLoanId() {
        // Given
        Integer loanId = sampleLoan.getLoanId(); // Pobieramy ID zapisanej pożyczki

        // When
        List<LoanItem> loanItems = loanItemService.findAllByLoanId(loanId);

        // Then
        assertNotNull(loanItems);
        assertFalse(loanItems.isEmpty());
        assertEquals(1, loanItems.size());
        assertEquals("Sample Loan Item", loanItems.get(0).getTitle());
    }

    @Test
    void testFindByLoanNumber() {
        // Given
        String loanNumber = sampleLoan.getLoanNumber(); // Pobieramy numer zapisanej pożyczki

        // When
        List<LoanItem> loanItems = loanItemService.findByLoanNumber(loanNumber);

        // Then
        assertNotNull(loanItems);
        assertFalse(loanItems.isEmpty());
        assertEquals(1, loanItems.size());
        assertEquals("Sample Loan Item", loanItems.get(0).getTitle());
    }
}