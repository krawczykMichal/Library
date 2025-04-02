package org.example.library.business;
import org.example.library.api.dto.BooksDTO;
import org.example.library.business.dao.BooksDao;
import org.example.library.domain.Authors;
import org.example.library.domain.Books;
import org.example.library.domain.Categories;
import org.example.library.domain.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BooksServiceTest {

    @Mock
    private CategoriesService categoriesService;

    @Mock
    private AuthorsService authorsService;

    @Mock
    private BooksDao booksDao;

    @InjectMocks
    private BooksService booksService;

    private BooksDTO booksDTO;
    private Books book;
    private Authors author;
    private Categories category;

    @BeforeEach
    void setUp() {
        booksDTO = BooksDTO.builder().title("Title")
                .isbn("1234567890")
                .publisher("Publisher")
                .publishedDate(2024)
                .copies(5)
                .booksAuthorCode("AUTH123")
                .booksCategoriesName("CATEGORY1")
                .build();
        author = Authors.builder()
                .authorCode("AUTH123")
                .name("John")
                .surname("Doe").build();
        category = Categories.builder().categoryId(1)
                .name("Fiction").build();
        book = Books.builder()
                .title("Title")
                .isbn("1234567890")
                .publisher("Publisher")
                .publishedDate(2024)
                .copies(5)
                .available(true)
                .build()
                .withAuthor(author)
                .withCategory(category);
    }

    @Test
    void shouldFindAllBooks() {
        when(booksDao.findAll()).thenReturn(List.of(book));
        List<Books> result = booksService.findAll();
        assertEquals(1, result.size());
        assertEquals("Title", result.get(0).getTitle());
        verify(booksDao, times(1)).findAll();
    }

    @Test
    void shouldSaveBook() {
        when(authorsService.findByAuthorCode("AUTH123")).thenReturn(author);
        when(categoriesService.findByName("CATEGORY1")).thenReturn(category);

        booksService.saveBook(booksDTO);
        verify(booksDao, times(1)).saveBook(any(Books.class));
    }

    @Test
    void shouldFindBookByIsbn() {
        when(booksDao.findByIsbn("1234567890")).thenReturn(Optional.of(book));
        Books result = booksService.findByIsbn("1234567890");
        assertEquals("Title", result.getTitle());
        verify(booksDao, times(2)).findByIsbn("1234567890");
    }

    @Test
    void shouldThrowExceptionWhenBookNotFoundByIsbn() {
        when(booksDao.findByIsbn("unknown")).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> booksService.findByIsbn("unknown"));
        verify(booksDao, times(1)).findByIsbn("unknown");
    }

    @Test
    void shouldUpdateBook() {
        when(booksDao.findByIsbn("1234567890")).thenReturn(Optional.of(book));
        booksDTO = BooksDTO.builder().title("New Title")
                .isbn("1234567890")
                .publisher("New Publisher")
                .publishedDate(2024)
                .copies(10)
                .booksAuthorCode("AUTH123")
                .booksCategoriesName("CATEGORY1").build();

        booksService.updateBook("1234567890", booksDTO);
        verify(booksDao, times(1)).saveBook(any(Books.class));
    }
}
