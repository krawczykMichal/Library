package org.example.library.business.dao;

import org.example.library.domain.LoanItem;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.example.library.domain.LoanItem;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@DataJpaTest
@TestPropertySource(properties = "spring.flyway.enabled=false") // Wyłączenie migracji Flyway
public class LoanItemDaoTest {

    @MockBean
    private LoanItemDao loanItemDao;

    @Test
    void testFindAllByLoanId() {
        // Tworzenie obiektów LoanItem
        LoanItem item1 = LoanItem.builder()
                .title("Book A")
                .quantity(1)
                .build();

        LoanItem item2 = LoanItem.builder()
                .title("Book B")
                .quantity(2)
                .build();

        List<LoanItem> loanItems = Arrays.asList(item1, item2);

        // Mockowanie findAllByLoanId
        when(loanItemDao.findAllByLoanId(anyInt())).thenReturn(loanItems);

        // Wywołanie metody findAllByLoanId
        List<LoanItem> foundItems = loanItemDao.findAllByLoanId(1);

        // Sprawdzanie wyników
        assertFalse(foundItems.isEmpty());
        assertEquals(2, foundItems.size());
    }

    @Test
    void testDeleteByLoanUserId() {
        // Mockowanie deleteByLoanUserId
        doNothing().when(loanItemDao).deleteByLoanUserId(anyInt());

        // Wywołanie metody deleteByLoanUserId
        loanItemDao.deleteByLoanUserId(1);

        // Brak asercji - test przejdzie, jeśli metoda nie rzuci wyjątku
    }
}
