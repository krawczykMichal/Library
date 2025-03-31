package org.example.library.business;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.example.library.api.dto.AuthorsDTO;
import org.example.library.business.dao.AuthorsDao;
import org.example.library.domain.Authors;
import org.example.library.domain.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AuthorsServiceTest {

    @Mock
    private AuthorsDao authorsDao;

    @InjectMocks
    private AuthorsService authorsService;

    private AuthorsDTO authorsDTO;
    private Authors author;

    @BeforeEach
    void setUp() {
        authorsDTO = AuthorsDTO.builder().name("John")
                .surname("Doe").build();
        author = Authors.builder()
                .name("John")
                .surname("Doe")
                .authorCode("1234567890")
                .build();
    }

    @Test
    void saveAuthor_ShouldSaveAuthor() {
        // When
        authorsService.saveAuthor(authorsDTO);

        // Then
        verify(authorsDao, times(1)).saveAuthor(any(Authors.class));
    }

    @Test
    void updateAuthor_ShouldUpdateAndSaveAuthor() {
        // Given
        Authors existingAuthor = author;
        AuthorsDTO authorsDTO = AuthorsDTO.builder()
                .name("Jane")
                .surname("Smith").build();

        when(authorsDao.saveAuthor(any(Authors.class))).thenReturn(null);

        // When
        authorsService.updateAuthor(existingAuthor, authorsDTO);

        // Then
        ArgumentCaptor<Authors> authorCaptor = ArgumentCaptor.forClass(Authors.class);
        verify(authorsDao).saveAuthor(authorCaptor.capture());

        Authors updatedAuthor = authorCaptor.getValue();
        assertEquals("Jane", updatedAuthor.getName());
        assertEquals("Smith", updatedAuthor.getSurname());
    }

    @Test
    void findAll_ShouldReturnListOfAuthors() {
        when(authorsDao.findAll()).thenReturn(List.of(author));
        List<Authors> result = authorsService.findAll();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getName());
    }

    @Test
    void findByAuthorCode_ShouldReturnAuthor_WhenExists() {
        when(authorsDao.findByAuthorCode("1234567890")).thenReturn(Optional.of(author));
        Authors result = authorsService.findByAuthorCode("1234567890");
        assertNotNull(result);
        assertEquals("John", result.getName());
    }

    @Test
    void findByAuthorCode_ShouldThrowNotFoundException_WhenNotExists() {
        when(authorsDao.findByAuthorCode("9999999999")).thenReturn(Optional.empty());
        NotFoundException exception = assertThrows(NotFoundException.class, () ->
                authorsService.findByAuthorCode("9999999999")
        );
        assertTrue(exception.getMessage().contains("Can't find author with author code"));
    }
}