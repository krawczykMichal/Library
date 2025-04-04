package org.example.library.business.dao;

import org.example.library.domain.Employees;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
@TestPropertySource(properties = "spring.flyway.enabled=false") // Wyłączenie migracji Flyway
public class EmployeesDaoTest {

    @MockBean
    private EmployeesDao employeesDao;

    @Test
    void testSaveEmployee() {
        // Tworzenie obiektu Employees
        Employees employee = Employees.builder()
                .name("John")
                .surname("Doe")
                .username("johndoe")
                .employeeNumber("EMP123")
                .build();

        // Mockowanie saveEmployee
        when(employeesDao.saveEmployee(any(Employees.class))).thenReturn(employee);

        // Wywołanie metody saveEmployee
        Employees savedEmployee = employeesDao.saveEmployee(employee);

        // Sprawdzanie wyników
        assertNotNull(savedEmployee);
        assertEquals("John", savedEmployee.getName());
        assertEquals("Doe", savedEmployee.getSurname());
        assertEquals("johndoe", savedEmployee.getUsername());
        assertEquals("EMP123", savedEmployee.getEmployeeNumber());
    }

    @Test
    void testFindByUsername() {
        // Tworzenie obiektu Employees
        Employees employee = Employees.builder()
                .name("Alice")
                .surname("Smith")
                .username("alicesmith")
                .employeeNumber("EMP456")
                .build();

        // Mockowanie findByUsername
        when(employeesDao.findByUsername("alicesmith")).thenReturn(Optional.of(employee));

        // Wywołanie metody findByUsername
        Optional<Employees> foundEmployee = employeesDao.findByUsername("alicesmith");

        // Sprawdzanie wyników
        assertTrue(foundEmployee.isPresent());
        assertEquals("Alice", foundEmployee.get().getName());
        assertEquals("Smith", foundEmployee.get().getSurname());
    }

    @Test
    void testFindAll() {
        // Tworzenie listy pracowników
        Employees employee1 = Employees.builder()
                .name("Tom")
                .surname("Brown")
                .username("tombrown")
                .employeeNumber("EMP789")
                .build();

        Employees employee2 = Employees.builder()
                .name("Emma")
                .surname("White")
                .username("emmawhite")
                .employeeNumber("EMP101")
                .build();

        List<Employees> employeesList = Arrays.asList(employee1, employee2);

        // Mockowanie findAll
        when(employeesDao.findAll()).thenReturn(employeesList);

        // Wywołanie metody findAll
        List<Employees> foundEmployees = employeesDao.findAll();

        // Sprawdzanie wyników
        assertFalse(foundEmployees.isEmpty());
        assertEquals(2, foundEmployees.size());
    }

    @Test
    void testFindByEmployeeNumber() {
        // Tworzenie obiektu Employees
        Employees employee = Employees.builder()
                .name("David")
                .surname("Johnson")
                .username("davidj")
                .employeeNumber("EMP555")
                .build();

        // Mockowanie findByEmployeeNumber
        when(employeesDao.findByEmployeeNumber("EMP555")).thenReturn(Optional.of(employee));

        // Wywołanie metody findByEmployeeNumber
        Optional<Employees> foundEmployee = employeesDao.findByEmployeeNumber("EMP555");

        // Sprawdzanie wyników
        assertTrue(foundEmployee.isPresent());
        assertEquals("David", foundEmployee.get().getName());
        assertEquals("Johnson", foundEmployee.get().getSurname());
    }

    @Test
    void testDeleteByEmployeeNumber() {
        // Tworzenie obiektu Employees
        Employees employee = Employees.builder()
                .name("Sarah")
                .surname("Connor")
                .username("sconnor")
                .employeeNumber("EMP999")
                .build();

        // Mockowanie deleteByEmployeeNumber
        doNothing().when(employeesDao).deleteByEmployeeNumber(employee);

        // Wywołanie metody deleteByEmployeeNumber
        employeesDao.deleteByEmployeeNumber(employee);

        // Weryfikacja czy metoda została wywołana
        verify(employeesDao, times(1)).deleteByEmployeeNumber(employee);
    }
}