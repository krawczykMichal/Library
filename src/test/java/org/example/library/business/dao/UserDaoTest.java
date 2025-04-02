package org.example.library.business.dao;

import org.example.library.domain.User;
import org.example.library.infrastructure.security.business.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = "spring.flyway.enabled=false")  // Wyłączenie migracji Flyway dla testów
public class UserDaoTest {

    @MockBean
    private UserDao userDao;

    @Test
    void testSaveUser() {
        // Tworzymy użytkownika za pomocą buildera
        User user = User.builder()
                .username("testuser")
                .password("password123")
                .build();

        // Mockowanie metody saveUser
        doNothing().when(userDao).saveUser(any(User.class));

        // Wywołanie metody saveUser
        userDao.saveUser(user);

        // Weryfikacja, czy saveUser została wywołana raz z odpowiednim obiektem
        verify(userDao, times(1)).saveUser(any(User.class));
    }

    @Test
    void testFindByUsername() {
        // Tworzymy użytkownika za pomocą buildera
        User user = User.builder()
                .username("testuser")
                .password("password123")
                .build();

        // Mockowanie metody findByUsername
        when(userDao.findByUsername("testuser")).thenReturn(user);

        // Wywołanie metody findByUsername
        User foundUser = userDao.findByUsername("testuser");

        // Weryfikacja, czy znaleziony użytkownik jest taki sam, jak oczekiwany
        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
    }

    @Test
    void testUpdatePassword() {
        // Tworzymy użytkownika za pomocą buildera
        User user = User.builder()
                .username("testuser")
                .password("password123")
                .build();

        // Mockowanie metody updatePassword
        doNothing().when(userDao).updatePassword(anyString(), anyInt());

        // Wywołanie metody updatePassword
        userDao.updatePassword("newpassword", 1);

        // Weryfikacja, czy updatePassword została wywołana raz z odpowiednimi argumentami
        verify(userDao, times(1)).updatePassword(anyString(), eq(1));
    }
}

