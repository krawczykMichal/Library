package org.example.library.business.dao;

import org.example.library.domain.Authors;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
@TestPropertySource(properties = "spring.flyway.enabled=false")  // Wyłączenie migracji Flyway dla testów
public class AuthorsDaoTest {

    @MockBean
    private AuthorsDao authorsDao;

    @Test
    void testSaveAuthor() {
        // Tworzenie obiektu Authors za pomocą buildera
        Authors author = Authors.builder()
                .name("John")
                .surname("Doe")
                .authorCode("JD123")
                .build();

        when(authorsDao.saveAuthor(any(Authors.class))).thenReturn(author);  // Mockujemy save

        // Zapisanie autora
        Authors savedAuthor = authorsDao.saveAuthor(author);

        assertNotNull(savedAuthor);
        assertEquals("John", savedAuthor.getName());
        assertEquals("Doe", savedAuthor.getSurname());
        assertEquals("JD123", savedAuthor.getAuthorCode());
    }

    @Test
    void testFindById() {
        // Tworzenie obiektu Authors z ustawionym authorId
        Authors author = Authors.builder()
                .authorId(1)  // Ustawienie authorId
                .name("Jane")
                .surname("Smith")
                .authorCode("JS456")
                .build();

        // Mockowanie findById, zwracamy Optional z autorem, którego id wynosi 1
        when(authorsDao.findById(1)).thenReturn(Optional.of(author));

        // Wywołanie metody findById z id 1
        Optional<Authors> foundAuthor = authorsDao.findById(1);

        // Sprawdzanie, czy autor jest obecny
        assertTrue(foundAuthor.isPresent());

        // Sprawdzanie wartości atrybutów autora
        assertEquals("Jane", foundAuthor.get().getName());
        assertEquals("Smith", foundAuthor.get().getSurname());
        assertEquals("JS456", foundAuthor.get().getAuthorCode());
    }

    @Test
    void testFindAll() {
        // Tworzenie obiektów Authors
        Authors author1 = Authors.builder()
                .name("John")
                .surname("Doe")
                .authorCode("JD123")
                .build();
        Authors author2 = Authors.builder()
                .name("Jane")
                .surname("Smith")
                .authorCode("JS456")
                .build();

        authorsDao.saveAuthor(author1);
        authorsDao.saveAuthor(author2);

        when(authorsDao.findAll()).thenReturn(List.of(author1, author2));  // Mockujemy findAll

        // Pobranie wszystkich autorów
        List<Authors> authorsList = authorsDao.findAll();

        assertNotNull(authorsList);
        assertEquals(2, authorsList.size());
    }

    @Test
    void testFindByAuthorCode() {
        // Tworzenie obiektu Authors
        Authors author = Authors.builder()
                .name("Mary")
                .surname("Johnson")
                .authorCode("MJ789")
                .build();

        authorsDao.saveAuthor(author);

        when(authorsDao.findByAuthorCode(anyString())).thenReturn(Optional.of(author));  // Mockujemy findByAuthorCode
        // Wyszukiwanie autora po kodzie
        Optional<Authors> foundAuthor = authorsDao.findByAuthorCode("MJ789");

        assertTrue(foundAuthor.isPresent());
        assertEquals("Mary", foundAuthor.get().getName());
        assertEquals("Johnson", foundAuthor.get().getSurname());
        assertEquals("MJ789", foundAuthor.get().getAuthorCode());
    }
}
