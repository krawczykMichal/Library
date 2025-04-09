package org.example.library.business;

import org.example.library.api.dto.AuthorsDTO;
import org.example.library.business.dao.AuthorsDao;
import org.example.library.domain.Authors;
import org.example.library.domain.exception.NotFoundException;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class AuthorsServiceTestIntegration {

    @Autowired
    private AuthorsService authorsService;

    @Autowired
    private AuthorsDao authorsDao;

    @Autowired
    private Flyway flyway;

    private AuthorsDTO authorsDTO;

    @BeforeEach
    void setUp() {
        flyway.clean();
        flyway.migrate();

        authorsDTO = AuthorsDTO.builder()
                .name("Jan")
                .surname("Kowalski")
                .authorCode("1234567890")
                .build();
    }

    @Test
    void testSaveAuthor() {
        // When
        Authors authors = authorsService.saveAuthor(authorsDTO);

        // Then
        Authors savedAuthor = authorsDao.findByAuthorCode(authors.getAuthorCode()).orElseThrow(() ->
                new AssertionError("Author with code '1234567890' not found"));

        assertEquals("Jan", savedAuthor.getName());
        assertEquals("Kowalski", savedAuthor.getSurname());
        assertEquals(authors.getAuthorCode(), savedAuthor.getAuthorCode());
    }


    @Test
    void testFindAll() {
        // Given
        Authors author1 = Authors.builder().name("Jan").surname("Kowalski").authorCode("123").build();
        Authors author2 = Authors.builder().name("Adam").surname("Nowak").authorCode("124").build();
        authorsDao.saveAuthor(author1);
        authorsDao.saveAuthor(author2);

        // When
        List<Authors> authorsList = authorsService.findAll();

        // Then
        assertEquals(77, authorsList.size());
    }

    @Test
    void testFindByAuthorCode() {
        // Given
        Authors author = Authors.builder()
                .name("Jan")
                .surname("Kowalski")
                .authorCode("1234567890")
                .build();
        authorsDao.saveAuthor(author);

        // When
        Authors foundAuthor = authorsService.findByAuthorCode("1234567890");

        // Then
        assertEquals("Jan", foundAuthor.getName());
        assertEquals("Kowalski", foundAuthor.getSurname());
        assertEquals("1234567890", foundAuthor.getAuthorCode());
    }

    @Test
    void testFindByAuthorCodeNotFound() {
        // When
        assertThrows(NotFoundException.class, () -> authorsService.findByAuthorCode("nonexistentCode"));
    }
}
