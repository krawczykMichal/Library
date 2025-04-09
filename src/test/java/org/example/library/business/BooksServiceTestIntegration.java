package org.example.library.business;

import org.example.library.api.dto.BooksDTO;
import org.example.library.business.dao.AuthorsDao;
import org.example.library.business.dao.BooksDao;
import org.example.library.business.dao.CategoriesDao;
import org.example.library.domain.Authors;
import org.example.library.domain.Books;
import org.example.library.domain.Categories;
import org.example.library.domain.exception.NotFoundException;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class BooksServiceTestIntegration {

    @Autowired
    private BooksService booksService;

    @Autowired
    private BooksDao booksDao;

    @Autowired
    private AuthorsDao authorsDao;

    @Autowired
    private CategoriesDao categoriesDao;

    @MockBean
    private AuthorsService authorsService;

    @MockBean
    private CategoriesService categoriesService;

    @Autowired
    private Flyway flyway;

    private Books sampleBook;
    private Authors sampleAuthor;
    private Categories sampleCategory;

    @BeforeEach
    void setUp() {
        flyway.clean();
        flyway.migrate();

        sampleAuthor = Authors.builder().authorCode("A123").name("John").surname("Doe").build();
        Authors authors = authorsDao.saveAuthor(sampleAuthor);
        sampleCategory = Categories.builder().name("Science Fiction").build();
        Categories categories = categoriesDao.saveCategory(sampleCategory);

        Authors persistedAuthor = authorsDao.findById(authors.getAuthorId()).orElseThrow();
        Categories persistedCategory = categoriesDao.findByName(categories.getName()).orElseThrow();


        sampleBook = Books.builder()
                .title("Dune")
                .isbn("123-456")
                .publisher("SciFi Press")
                .publishedDate(2020)
                .copies(10)
                .available(true)
                .author(persistedAuthor)
                .category(persistedCategory)
                .build();

    }

    @Test
    @Transactional
    void testSaveBook() {
        // Given
        when(authorsService.findByAuthorCode("A123")).thenReturn(sampleAuthor);
        when(categoriesService.findByName("Science Fiction")).thenReturn(sampleCategory);

        // When
        booksDao.saveBook(sampleBook);

        // Then
        Books savedBook = booksDao.findByIsbn("123-456").orElseThrow(() -> new AssertionError("Book not found"));
        assertEquals("Dune", savedBook.getTitle());
        assertEquals("SciFi Press", savedBook.getPublisher());
        assertEquals(10, savedBook.getCopies());
        assertEquals(true, savedBook.getAvailable());
        assertEquals(sampleAuthor, savedBook.getAuthor());
        assertEquals(sampleCategory, savedBook.getCategory());
    }

    @Test
    @Transactional
    void testFindAllBooks() {
        // Given
        Books books1 = booksService.findAll().getFirst();

        // When
        List<Books> books = booksService.findAll();

        // Then
        assertEquals(150, books.size());
        assertEquals(books1.getTitle(), books.getFirst().getTitle());
    }

    @Test
    void testFindBookByIsbn() {
        // Given
        booksDao.saveBook(sampleBook);

        // When
        Books foundBook = booksService.findByIsbn("123-456");

        // Then
        assertEquals("Dune", foundBook.getTitle());
        assertEquals("SciFi Press", foundBook.getPublisher());
    }

    @Test
    void testFindBookByIsbnNotFound() {
        // When
        assertThrows(NotFoundException.class, () -> booksService.findByIsbn("999-999"));
    }

    @Test
    @Transactional
    void testUpdateBook() {
        // Given
        Books books2 = booksDao.saveBook(sampleBook);
        Books books = booksDao.findByIsbn("123-456").orElseThrow(() -> new AssertionError("Book not found"));

        BooksDTO updatedBookDTO = BooksDTO.builder()
                .title("Dune 2")
                .isbn("123-456")
                .publisher("SciFi Press")
                .copies(5)
                .build();

        Books books1 = books2.withTitle(updatedBookDTO.getTitle())
                .withPublisher(updatedBookDTO.getPublisher())
                .withIsbn(updatedBookDTO.getIsbn())
                .withCopies(updatedBookDTO.getCopies());

        // When
        booksDao.saveBook(books1);

        // Then
        Books updatedBook = booksDao.findByIsbn("123-456").orElseThrow(() -> new AssertionError("Book not found"));
        assertEquals("Dune 2", updatedBook.getTitle());
        assertEquals(5, updatedBook.getCopies());
        assertEquals(2020, updatedBook.getPublishedDate());
    }
}
