package org.example.library.business;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.example.library.api.dto.PasswordDTO;
import org.example.library.api.dto.UsersDTO;
import org.example.library.business.dao.*;
import org.example.library.domain.Role;
import org.example.library.domain.User;
import org.example.library.domain.Users;
import org.example.library.domain.exception.NotFoundException;
import org.example.library.infrastructure.security.business.dao.RoleDao;
import org.example.library.infrastructure.security.business.dao.UserDao;
import org.example.library.infrastructure.security.business.dao.UserRoleDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    @Mock
    private UsersDao usersDao;

    @Mock
    private UserDao userDao;

    @Mock
    private RoleDao roleDao;

    @Mock
    private CartDao cartDao;

    @Mock
    private ReservationsDao reservationsDao;

    @Mock
    private ReservationItemDao reservationItemDao;

    @Mock
    private LoanRequestDao loanRequestDao;

    @Mock
    private LoanRequestItemDao loanRequestItemDao;

    @Mock
    private LoansDao loansDao;

    @Mock
    private LoanItemDao loanItemDao;

    @Mock
    private UserRoleDao userRoleDao;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsersService usersService;

    private Users user;
    private User userEntity;
    private UsersDTO usersDTO;
    private Role role;

    @BeforeEach
    void setUp() {
        userEntity = User.builder()
                .username("testuser")
                .email("test@example.com")
                .password("hashedpassword")
                .active(true)
                .role(Set.of())
                .build();

        user = Users.builder()
                .name("Test")
                .surname("User")
                .username("testuser")
                .email("test@example.com")
                .phoneNumber("123456789")
                .user(userEntity)
                .build();

        PasswordDTO passwordDTO = new PasswordDTO("password", "password");
        usersDTO = UsersDTO.builder().name("Test")
                .surname("User")
                .username("testuser")
                .email("test@example.com")
                .phoneNumber("123456789")
                .passwordDTO(passwordDTO).build();
        role = Role.builder().roleId(1)
                .role("USER").build();
    }

    @Test
    void testSaveUser() {
        // Mamy teraz mockowanie metod saveUser
        when(usersDao.saveUser(any(Users.class))).thenReturn(user);
        doNothing().when(userDao).saveUser(any(User.class));


        assertDoesNotThrow(() -> usersService.saveUser(usersDTO));

        verify(usersDao, times(1)).saveUser(any(Users.class));
        verify(userDao, times(1)).saveUser(any(User.class));
    }

    @Test
    void testAssignRoleToUser() {
        // Przygotowanie danych
        User userEntity = User.builder()
                .userId(1)  // Przypisanie identyfikatora
                .username("testuser")
                .email("test@example.com")
                .password("hashedpassword")
                .active(true)
                .role(Set.of())
                .build();

        when(userDao.findByUsername("testuser")).thenReturn(userEntity);
        when(roleDao.findRoleByName("USER")).thenReturn(Optional.of(role));

        // Wywołanie metody
        usersService.assignRoleToUser("testuser");

        // Weryfikacja
        verify(userRoleDao, times(1)).saveUserRole(1, 1);  // Upewnienie się, że wywołana została metoda z poprawnymi argumentami
    }

    @Test
    void testFindByUsername_UserExists() {
        when(usersDao.findByUsername("testuser")).thenReturn(Optional.of(user));

        Users foundUser = usersService.findByUsername("testuser");
        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
    }

    @Test
    void testFindByUsername_UserNotFound() {
        when(usersDao.findByUsername("testuser")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> usersService.findByUsername("testuser"));
    }

    @Test
    void testDeleteById() {
        usersService.deleteById(1);
        verify(loanItemDao, times(1)).deleteByLoanUserId(1);
        verify(loansDao, times(1)).deleteByUserId(1);
        verify(usersDao, times(1)).deleteById(1);
    }
}