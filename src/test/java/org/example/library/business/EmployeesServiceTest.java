package org.example.library.business;
import org.example.library.api.dto.EmployeesDTO;
import org.example.library.business.dao.EmployeesDao;
import org.example.library.domain.Employees;
import org.example.library.domain.exception.NotFoundException;
import org.example.library.domain.exception.UserNameAlreadyTakenException;
import org.example.library.domain.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeesServiceTest {

    @Mock
    private EmployeesDao employeesDao;

    @InjectMocks
    private EmployeesService employeesService;

    private EmployeesDTO validEmployeeDTO;
    private Employees existingEmployee;

    @BeforeEach
    void setUp() {
        // Tworzymy przykładowy DTO używając buildera
        validEmployeeDTO = EmployeesDTO.builder()
                .name("John")
                .surname("Doe")
                .username("john.doe")
                .email("john.doe@example.com")
                .build();

        // Tworzymy przykładowego pracownika, zakładając, że employeeNumber jest już ustawiony
        existingEmployee = Employees.builder()
                .name("John")
                .surname("Doe")
                .username("john.doe")
                .email("john.doe@example.com")
                .employeeNumber("1234567890")
                .build();
    }

    @Test
    void shouldSaveEmployee() {
        // Jeśli metoda saveEmployee nie zwraca wartości istotnej dla testu, możemy ustawić zwracanie null
        when(employeesDao.saveEmployee(any(Employees.class))).thenReturn(null);

        employeesService.saveEmployee(validEmployeeDTO);

        ArgumentCaptor<Employees> captor = ArgumentCaptor.forClass(Employees.class);
        verify(employeesDao, times(1)).saveEmployee(captor.capture());

        Employees savedEmployee = captor.getValue();
        // Sprawdzamy, czy pozostałe dane pochodzą z DTO
        assertEquals(validEmployeeDTO.getName(), savedEmployee.getName());
        assertEquals(validEmployeeDTO.getSurname(), savedEmployee.getSurname());
        assertEquals(validEmployeeDTO.getUsername(), savedEmployee.getUsername());
        assertEquals(validEmployeeDTO.getEmail(), savedEmployee.getEmail());
        // Sprawdzamy, czy numer pracownika ma 10 cyfr
        assertNotNull(savedEmployee.getEmployeeNumber());
        assertEquals(10, savedEmployee.getEmployeeNumber().length());
        assertTrue(Pattern.matches("\\d{10}", savedEmployee.getEmployeeNumber()));
    }

    @Test
    void shouldFindEmployeeByUsername() {
        when(employeesDao.findByUsername("john.doe")).thenReturn(Optional.of(existingEmployee));

        Employees found = employeesService.findByUsername("john.doe");

        assertNotNull(found);
        assertEquals("john.doe", found.getUsername());
        verify(employeesDao, times(1)).findByUsername("john.doe");
    }

    @Test
    void shouldThrowExceptionWhenEmployeeNotFoundByUsername() {
        when(employeesDao.findByUsername("unknown")).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class,
                () -> employeesService.findByUsername("unknown"));

        assertTrue(exception.getMessage().contains("Could not find employee with username"));
        verify(employeesDao, times(1)).findByUsername("unknown");
    }

    @Test
    void shouldReturnAllEmployees() {
        when(employeesDao.findAll()).thenReturn(List.of(existingEmployee));

        List<Employees> employees = employeesService.findAll();

        assertNotNull(employees);
        assertEquals(1, employees.size());
        verify(employeesDao, times(1)).findAll();
    }

    @Test
    void shouldFindEmployeeByEmployeeNumber() {
        when(employeesDao.findByEmployeeNumber("1234567890")).thenReturn(Optional.of(existingEmployee));

        Employees found = employeesService.findByEmployeeNumber("1234567890");

        assertNotNull(found);
        assertEquals("1234567890", found.getEmployeeNumber());
        verify(employeesDao, times(1)).findByEmployeeNumber("1234567890");
    }

    @Test
    void shouldThrowExceptionWhenEmployeeNotFoundByEmployeeNumber() {
        when(employeesDao.findByEmployeeNumber("0000000000")).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class,
                () -> employeesService.findByEmployeeNumber("0000000000"));

        assertTrue(exception.getMessage().contains("Could not find employee with employeeNumber"));
        verify(employeesDao, times(1)).findByEmployeeNumber("0000000000");
    }

    @Test
    void shouldUpdateEmployeeSuccessfully() {
        // Przygotowanie danych aktualizacyjnych
        EmployeesDTO updateDTO = EmployeesDTO.builder()
                .name("Jane")
                .surname("Smith")
                .username("jane.smith")
                .email("jane.smith@example.com")
                .build();

        // Istniejący pracownik, którego aktualizujemy
        existingEmployee = Employees.builder()
                .name("John")
                .surname("Doe")
                .username("john.doe")
                .email("john.doe@example.com")
                .employeeNumber("1234567890")
                .build();

        // Gdy szukamy po numerze, zwracamy istniejącego pracownika
        when(employeesDao.findByEmployeeNumber("1234567890")).thenReturn(Optional.of(existingEmployee));
        // Gdy sprawdzamy username, zakładamy, że nie ma kolizji (zwracamy Optional.empty())
        when(employeesDao.findByUsername(updateDTO.getUsername())).thenReturn(Optional.empty());

        // Gdy zapisujemy pracownika, zwracamy obiekt zaktualizowany
        when(employeesDao.saveEmployee(any(Employees.class))).thenAnswer(invocation -> invocation.getArgument(0));

        employeesService.updateEmployee("1234567890", updateDTO);

        ArgumentCaptor<Employees> captor = ArgumentCaptor.forClass(Employees.class);
        verify(employeesDao, times(1)).saveEmployee(captor.capture());
        Employees updatedEmployee = captor.getValue();

        // Sprawdzamy, czy dane zostały zaktualizowane zgodnie z DTO
        assertEquals("Jane", updatedEmployee.getName());
        assertEquals("Smith", updatedEmployee.getSurname());
        assertEquals("jane.smith", updatedEmployee.getUsername());
        assertEquals("jane.smith@example.com", updatedEmployee.getEmail());
    }

    @Test
    void shouldThrowExceptionWhenUsernameAlreadyTakenDuringUpdate() {
        // Ustawiamy scenariusz: istnieje już pracownik z nowym username
        EmployeesDTO updateDTO = EmployeesDTO.builder()
                .name("Jane")
                .surname("Smith")
                .username("taken.username")
                .email("jane.smith@example.com")
                .build();

        existingEmployee = Employees.builder()
                .name("John")
                .surname("Doe")
                .username("john.doe")
                .email("john.doe@example.com")
                .employeeNumber("1234567890")
                .build();

        // Gdy szukamy pracownika po numerze, zwracamy istniejącego pracownika
        when(employeesDao.findByEmployeeNumber("1234567890")).thenReturn(Optional.of(existingEmployee));
        // Gdy szukamy po username nowego użytkownika, symulujemy, że taki już istnieje
        when(employeesDao.findByUsername("taken.username")).thenReturn(Optional.of(Employees.builder().build()));

        UserNameAlreadyTakenException exception = assertThrows(UserNameAlreadyTakenException.class,
                () -> employeesService.updateEmployee("1234567890", updateDTO));

        assertTrue(exception.getMessage().contains("Username 'taken.username' is already taken."));
    }

    @Test
    void shouldThrowValidationExceptionForInvalidName() {
        // Przykład: imię zawiera niedozwolone znaki (np. cyfry)
        EmployeesDTO updateDTO = EmployeesDTO.builder()
                .name("John123")
                .surname("Doe")
                .username("john.doe")
                .email("john.doe@example.com")
                .build();

        when(employeesDao.findByEmployeeNumber("1234567890")).thenReturn(Optional.of(existingEmployee));
        // Nie musimy stubować employeesDao.findByUsername, bo nie zostanie wywołane, gdy username pozostaje niezmienione

        ValidationException exception = assertThrows(ValidationException.class,
                () -> employeesService.updateEmployee("1234567890", updateDTO));

        assertTrue(exception.getMessage().contains("Name can only contain letters"));

    }

    @Test
    void shouldDeleteEmployeeByEmployeeNumber() {
        when(employeesDao.findByEmployeeNumber("1234567890")).thenReturn(Optional.of(existingEmployee));

        doNothing().when(employeesDao).deleteByEmployeeNumber(existingEmployee);

        employeesService.deleteByEmployeeNumber("1234567890");

        verify(employeesDao, times(1)).deleteByEmployeeNumber(existingEmployee);
    }
}
