package org.example.library.business;

import org.example.library.business.dao.BooksDao;
import org.example.library.business.dao.LoanItemDao;
import org.example.library.business.dao.LoansDao;
import org.example.library.domain.*;
import org.example.library.domain.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoansServiceTest {

    @Mock
    private LoansDao loansDao;

    @Mock
    private BooksDao booksDao;

    @Mock
    private LoanItemDao loanItemDao;

    @InjectMocks
    private LoansService loansService;

    private LoanRequest loanRequest;
    private List<LoanRequestItem> loanRequestItemList;
    private Users user;
    private Books book1;
    private Books book2;
    private LoanRequestItem loanRequestItem1;
    private LoanRequestItem loanRequestItem2;

    @BeforeEach
    void setUp() {
        user = Users.builder()
                .userId(1)
                .username("testuser")
                .build();

        // Przygotowanie LoanRequest bez pozycji (pozycje przekazywane osobno)
        loanRequest = LoanRequest.builder()
                .loanRequestNumber("LR123")
                .requestDate(LocalDateTime.now())
                .build()
                .withUser(user);

        // Przygotowanie przykładowych książek
        book1 = Books.builder()
                .title("Book 1")
                .copies(5)
                .build();

        book2 = Books.builder()
                .title("Book 2")
                .copies(3)
                .build();

        // Przygotowanie pozycji LoanRequestItem
        loanRequestItem1 = LoanRequestItem.builder()
                .title("Book 1")
                .book(book1)
                .quantity(2)
                .build();

        loanRequestItem2 = LoanRequestItem.builder()
                .title("Book 2")
                .book(book2)
                .quantity(1)
                .build();

        loanRequestItemList = List.of(loanRequestItem1, loanRequestItem2);
    }

    @Test
    void testMakeLoan() {
        // Wywołanie metody makeLoan
        loansService.makeLoan(loanRequest, loanRequestItemList);

        // Przechwycenie obiektu Loans zapisanego przez DAO
        ArgumentCaptor<Loans> captor = ArgumentCaptor.forClass(Loans.class);
        verify(loansDao, times(1)).save(captor.capture());
        Loans savedLoan = captor.getValue();

        // Sprawdzamy, czy loanNumber ma 10 cyfr
        assertNotNull(savedLoan.getLoanNumber());
        assertEquals(10, savedLoan.getLoanNumber().length());
        assertTrue(Pattern.matches("\\d{10}", savedLoan.getLoanNumber()));

        // Sprawdzamy, czy użytkownik jest poprawnie ustawiony
        assertEquals(user, savedLoan.getUser());

        // Sprawdzamy, czy lista LoanItem została poprawnie utworzona na podstawie LoanRequestItem
        List<LoanItem> loanItems = savedLoan.getLoanItem();
        assertNotNull(loanItems);
        assertEquals(2, loanItems.size());
        // Porównujemy pola pozycji
        LoanItem item1 = loanItems.get(0);
        assertEquals(loanRequestItem1.getTitle(), item1.getTitle());
        assertEquals(loanRequestItem1.getBook(), item1.getBook());
        assertEquals(loanRequestItem1.getQuantity(), item1.getQuantity());
        LoanItem item2 = loanItems.get(1);
        assertEquals(loanRequestItem2.getTitle(), item2.getTitle());
        assertEquals(loanRequestItem2.getBook(), item2.getBook());
        assertEquals(loanRequestItem2.getQuantity(), item2.getQuantity());
    }

    @Test
    void testFindAllByUserId() {
        List<Loans> expectedLoans = new ArrayList<>();
        expectedLoans.add(Loans.builder().loanNumber("1111111111").build());
        when(loansDao.findAllByUserId(1)).thenReturn(expectedLoans);

        List<Loans> result = loansService.findAllByUserId(1);
        assertEquals(expectedLoans, result);
        verify(loansDao, times(1)).findAllByUserId(1);
    }

    @Test
    void testFindAllByUserIdWithReturned() {
        List<Loans> expectedLoans = new ArrayList<>();
        expectedLoans.add(Loans.builder().loanNumber("2222222222").build());
        when(loansDao.findAllByUserId(1, true)).thenReturn(expectedLoans);

        List<Loans> result = loansService.findAllByUserId(1, true);
        assertEquals(expectedLoans, result);
        verify(loansDao, times(1)).findAllByUserId(1, true);
    }

    @Test
    void testFindByIdFound() {
        Loans loan = Loans.builder()
                .loanNumber("3333333333")
                .build();
        when(loansDao.findById(1)).thenReturn(Optional.of(loan));

        Loans result = loansService.findById(1);
        assertNotNull(result);
        assertEquals("3333333333", result.getLoanNumber());
        verify(loansDao, times(1)).findById(1);
    }

    @Test
    void testFindByIdNotFound() {
        when(loansDao.findById(2)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> loansService.findById(2));
        assertTrue(exception.getMessage().contains("Cannot find loan with id 2"));
    }

    @Test
    void testFindAllForEmployee() {
        List<Loans> expectedList = List.of(Loans.builder().loanNumber("4444444444").build());
        when(loansDao.findAllForEmployee()).thenReturn(expectedList);

        List<Loans> result = loansService.findAllForEmployee();
        assertEquals(expectedList, result);
        verify(loansDao, times(1)).findAllForEmployee();
    }

    @Test
    void testFindByLoanNumberFound() {
        Loans loan = Loans.builder().loanNumber("5555555555").build();
        when(loansDao.findByLoanNumber("5555555555")).thenReturn(Optional.of(loan));

        Loans result = loansService.findByLoanNumber("5555555555");
        assertNotNull(result);
        assertEquals("5555555555", result.getLoanNumber());
        verify(loansDao, times(1)).findByLoanNumber("5555555555");
    }

    @Test
    void testFindByLoanNumberNotFound() {
        when(loansDao.findByLoanNumber("6666666666")).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> loansService.findByLoanNumber("6666666666"));
        assertTrue(exception.getMessage().contains("Cannot find loan with loanNumber 6666666666"));
    }

    @Test
    void testReturnLoan() {
        // Przygotowujemy przykładową Loans, która ma numer pożyczki
        Loans loan = Loans.builder()
                .loanNumber("7777777777")
                .build();
        // Stubujemy wyszukiwanie po numerze
        when(loansDao.findByLoanNumber("7777777777")).thenReturn(Optional.of(loan));

        // Przygotowujemy przykładową listę LoanItem, gdzie każdy LoanItem zawiera książkę
        LoanItem loanItem1 = LoanItem.builder()
                .title("Book X")
                .book(Books.builder().title("Book X").copies(5).build())
                .quantity(2)
                .build();
        LoanItem loanItem2 = LoanItem.builder()
                .title("Book Y")
                .book(Books.builder().title("Book Y").copies(3).build())
                .quantity(1)
                .build();

        List<LoanItem> loanItems = List.of(loanItem1, loanItem2);
        when(loanItemDao.findByLoanNumber("7777777777")).thenReturn(loanItems);

        // Stubujemy zapis książki (metoda saveBook zwraca np. null lub samą książkę)
        when(booksDao.saveBook(any(Books.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Wywołujemy metodę returnLoan
        loansService.returnLoan("7777777777");

        // Weryfikujemy, że dla każdej pozycji została wywołana metoda zapisu książki
        // Książka "Book X": copies 5 + 2 = 7, "Book Y": copies 3 + 1 = 4.
        ArgumentCaptor<Books> booksCaptor = ArgumentCaptor.forClass(Books.class);
        verify(booksDao, times(2)).saveBook(booksCaptor.capture());
        List<Books> savedBooks = booksCaptor.getAllValues();

        Books savedBookX = savedBooks.stream().filter(b -> "Book X".equals(b.getTitle())).findFirst().orElse(null);
        Books savedBookY = savedBooks.stream().filter(b -> "Book Y".equals(b.getTitle())).findFirst().orElse(null);
        assertNotNull(savedBookX);
        assertEquals(7, savedBookX.getCopies());
        assertNotNull(savedBookY);
        assertEquals(4, savedBookY.getCopies());

        // Weryfikujemy, że wywołano metodę zwracania pożyczki w DAO
        verify(loansDao, times(1)).returnLoan("7777777777");
    }
}
