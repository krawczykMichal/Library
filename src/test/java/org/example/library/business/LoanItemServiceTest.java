package org.example.library.business;
import org.example.library.business.dao.LoanItemDao;
import org.example.library.domain.LoanItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanItemServiceTest {

    @Mock
    private LoanItemDao loanItemDao;

    @InjectMocks
    private LoanItemService loanItemService;

    @Test
    void testFindAllByLoanId() {
        // Tworzymy przykładowe obiekty LoanItem bez powiązania z Loan
        LoanItem item1 = LoanItem.builder()
                .loanItemId(1)
                .title("Book 1")
                .build();

        LoanItem item2 = LoanItem.builder()
                .loanItemId(2)
                .title("Book 2")
                .build();

        List<LoanItem> expectedItems = List.of(item1, item2);

        when(loanItemDao.findAllByLoanId(1)).thenReturn(expectedItems);

        List<LoanItem> actualItems = loanItemService.findAllByLoanId(1);

        assertNotNull(actualItems);
        assertEquals(2, actualItems.size());
        assertEquals("Book 1", actualItems.get(0).getTitle());
        verify(loanItemDao, times(1)).findAllByLoanId(1);
    }

    @Test
    void testFindByLoanNumber() {
        // Tworzymy przykładowe obiekty LoanItem bez bezpośredniego powiązania z Loan
        LoanItem item = LoanItem.builder()
                .loanItemId(3)
                .title("Book 3")
                .build();

        List<LoanItem> expectedItems = List.of(item);

        when(loanItemDao.findByLoanNumber("LN123")).thenReturn(expectedItems);

        List<LoanItem> actualItems = loanItemService.findByLoanNumber("LN123");

        assertNotNull(actualItems);
        assertEquals(1, actualItems.size());
        assertEquals("Book 3", actualItems.get(0).getTitle());
        verify(loanItemDao, times(1)).findByLoanNumber("LN123");
    }
}

