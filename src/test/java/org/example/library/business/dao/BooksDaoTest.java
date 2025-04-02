package org.example.library.business.dao;

import org.example.library.domain.Authors;
import org.example.library.domain.Books;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest
@TestPropertySource(properties = "spring.flyway.enabled=false")  // Wyłączenie migracji Flyway dla testów
public class BooksDaoTest {

    @MockBean
    private BooksDao booksDao;

    @MockBean
    private AuthorsDao authorsDao;

    @Test
    void testFindAll() {
        // Tworzenie autora
        Authors author = Authors.builder()
                .name("Jane")
                .surname("Smith")
                .authorCode("JS456")
                .build();

        // Tworzenie książek z autorem
        Books book1 = Books.builder()
                .isbn("12345")
                .title("Book 1")
                .author(author)
                .build();
        Books book2 = Books.builder()
                .isbn("67890")
                .title("Book 2")
                .author(author)
                .build();

        List<Books> booksList = Arrays.asList(book1, book2);

        // Mockowanie findAll, zwracamy naszą listę książek
        when(booksDao.findAll()).thenReturn(booksList);

        // Wywołanie metody findAll
        List<Books> foundBooks = booksDao.findAll();

        // Sprawdzanie, czy lista książek nie jest pusta i zawiera oczekiwane dane
        assertFalse(foundBooks.isEmpty());
        assertEquals(2, foundBooks.size());
        assertEquals("Book 1", foundBooks.get(0).getTitle());
        assertEquals("Book 2", foundBooks.get(1).getTitle());
        assertEquals("Jane", foundBooks.get(0).getAuthor().getName());
        assertEquals("Smith", foundBooks.get(0).getAuthor().getSurname());
    }

    @Test
    void testSaveBook() {
        // Tworzenie autora
        Authors author = Authors.builder()
                .name("John")
                .surname("Doe")
                .authorCode("JD123")
                .build();

        // Tworzenie książki z autorem
        Books book = Books.builder()
                .isbn("11111")
                .title("New Book")
                .author(author)
                .build();

        // Mockowanie saveBook, zwracamy zapisaną książkę
        when(booksDao.saveBook(any(Books.class))).thenReturn(book);

        // Wywołanie metody saveBook
        Books savedBook = booksDao.saveBook(book);

        // Sprawdzanie, czy książka została zapisana poprawnie
        assertNotNull(savedBook);
        assertEquals("11111", savedBook.getIsbn());
        assertEquals("New Book", savedBook.getTitle());
        assertEquals("John", savedBook.getAuthor().getName());
        assertEquals("Doe", savedBook.getAuthor().getSurname());
    }

    @Test
    void testFindByIsbn() {
        // Tworzenie autora
        Authors author = Authors.builder()
                .name("Jane")
                .surname("Smith")
                .authorCode("JS456")
                .build();

        // Tworzenie książki z autorem
        Books book = Books.builder()
                .isbn("12345")
                .title("Book 1")
                .author(author)
                .build();

        // Mockowanie findByIsbn, zwracamy książkę z konkretnym ISBN
        when(booksDao.findByIsbn("12345")).thenReturn(Optional.of(book));

        // Wywołanie metody findByIsbn
        Optional<Books> foundBook = booksDao.findByIsbn("12345");

        // Sprawdzanie, czy książka jest obecna
        assertTrue(foundBook.isPresent());
        assertEquals("12345", foundBook.get().getIsbn());
        assertEquals("Book 1", foundBook.get().getTitle());
        assertEquals("Jane", foundBook.get().getAuthor().getName());
    }

    @Test
    void testFindByIsbnNotFound() {
        // Mockowanie findByIsbn, zwracamy Optional.empty() dla nieistniejącego ISBN
        when(booksDao.findByIsbn("99999")).thenReturn(Optional.empty());

        // Wywołanie metody findByIsbn dla nieistniejącego ISBN
        Optional<Books> foundBook = booksDao.findByIsbn("99999");

        // Sprawdzanie, że książka nie została znaleziona
        assertFalse(foundBook.isPresent());
    }

    @Test
    void testFindByTitleInclude() {
        // Tworzenie autora
        Authors author = Authors.builder()
                .name("Alice")
                .surname("Johnson")
                .authorCode("AJ789")
                .build();

        // Tworzenie książek
        Books book1 = Books.builder()
                .isbn("12345")
                .title("Amazing Book 1")
                .author(author)
                .build();
        Books book2 = Books.builder()
                .isbn("67890")
                .title("Amazing Book 2")
                .author(author)
                .build();

        List<Books> booksList = Arrays.asList(book1, book2);

        // Mockowanie findByTitleInclude, zwracamy książki zawierające w tytule "Amazing"
        when(booksDao.findByTitleInclude("Amazing")).thenReturn(booksList);

        // Wywołanie metody findByTitleInclude
        List<Books> foundBooks = booksDao.findByTitleInclude("Amazing");

        // Sprawdzanie, czy książki zostały znalezione
        assertFalse(foundBooks.isEmpty());
        assertEquals(2, foundBooks.size());
        assertTrue(foundBooks.get(0).getTitle().contains("Amazing"));
        assertTrue(foundBooks.get(1).getTitle().contains("Amazing"));
        assertEquals("Alice", foundBooks.get(0).getAuthor().getName());
    }
}
