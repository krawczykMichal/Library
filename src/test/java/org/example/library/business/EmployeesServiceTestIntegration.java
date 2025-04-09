package org.example.library.business;

import org.example.library.api.dto.EmployeesDTO;
import org.example.library.business.dao.EmployeesDao;
import org.example.library.domain.Employees;
import org.example.library.domain.exception.NotFoundException;
import org.example.library.domain.exception.UserNameAlreadyTakenException;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class EmployeesServiceTestIntegration {

    @Autowired
    private EmployeesService employeesService;

    @Autowired
    private EmployeesDao employeesDao;

    @Autowired
    private Flyway flyway;

    private EmployeesDTO sampleEmployeeDTO;

    @BeforeEach
    void setUp() {
        flyway.clean();
        flyway.migrate();

        // Przygotowanie przykładowych danych
        sampleEmployeeDTO = EmployeesDTO.builder()
                .name("John")
                .surname("Doe")
                .username("john_doe")
                .email("john.doe@example.com")
                .build();
    }

    @Test
    void testSaveEmployee() {
        // Given
        // Użycie sampleEmployeeDTO do zapisania pracownika

        // When
        employeesService.saveEmployee(sampleEmployeeDTO);

        // Then
        Employees savedEmployee = employeesService.findByUsername("john_doe");
        assertNotNull(savedEmployee);
        assertEquals("John", savedEmployee.getName());
        assertEquals("Doe", savedEmployee.getSurname());
        assertEquals("john_doe", savedEmployee.getUsername());
        assertEquals("john.doe@example.com", savedEmployee.getEmail());
        assertNotNull(savedEmployee.getEmployeeNumber()); // Sprawdzenie, czy numer pracownika został wygenerowany
    }

    @Test
    void testFindByUsername() {
        // Given
        employeesService.saveEmployee(sampleEmployeeDTO);

        // When
        Employees foundEmployee = employeesService.findByUsername("john_doe");

        // Then
        assertNotNull(foundEmployee);
        assertEquals("john_doe", foundEmployee.getUsername());
    }

    @Test
    void testFindByEmployeeNumber() {
        // Given
        employeesService.saveEmployee(sampleEmployeeDTO);
        String employeeNumber = employeesDao.findByUsername("john_doe").get().getEmployeeNumber();

        // When
        Employees foundEmployee = employeesService.findByEmployeeNumber(employeeNumber);

        // Then
        assertNotNull(foundEmployee);
        assertEquals(employeeNumber, foundEmployee.getEmployeeNumber());
    }

    @Test
    void testUpdateEmployee() {
        // Given
        employeesService.saveEmployee(sampleEmployeeDTO);
        String employeeNumber = employeesDao.findByUsername("john_doe").get().getEmployeeNumber();
        EmployeesDTO updatedEmployeeDTO = EmployeesDTO.builder()
                .name("Jane")
                .surname("Doe")
                .username("jane_doe")
                .email("jane.doe@example.com")
                .build();

        // When
        employeesService.updateEmployee(employeeNumber, updatedEmployeeDTO);

        // Then
        Employees updatedEmployee = employeesService.findByEmployeeNumber(employeeNumber);
        assertNotNull(updatedEmployee);
        assertEquals("Jane", updatedEmployee.getName());
        assertEquals("Doe", updatedEmployee.getSurname());
        assertEquals("jane_doe", updatedEmployee.getUsername());
        assertEquals("jane.doe@example.com", updatedEmployee.getEmail());
    }

    @Test
    void testUpdateEmployeeThrowsUsernameAlreadyTakenException() {
        // Given
        employeesService.saveEmployee(sampleEmployeeDTO);
        String employeeNumber = employeesDao.findByUsername("john_doe").get().getEmployeeNumber();
        EmployeesDTO secondEmployeeDTO = EmployeesDTO.builder()
                .name("Alice")
                .surname("Smith")
                .username("alice_smith") // Inny username
                .email("alice.smith@example.com")
                .build();
        employeesService.saveEmployee(secondEmployeeDTO);


        EmployeesDTO updatedEmployeeDTO = EmployeesDTO.builder()
                .name("John")
                .surname("Doe")
                .username("alice_smith")
                .email("john.doe@example.com")
                .build();

        // When & Then
        assertThrows(UserNameAlreadyTakenException.class, () -> {
            employeesService.updateEmployee(employeeNumber, updatedEmployeeDTO);
        });
    }

    @Test
    void testDeleteEmployee() {
        // Given
        employeesService.saveEmployee(sampleEmployeeDTO);
        String employeeNumber = employeesDao.findByUsername("john_doe").get().getEmployeeNumber();

        // When
        employeesService.deleteByEmployeeNumber(employeeNumber);

        // Then
        assertThrows(NotFoundException.class, () -> {
            employeesService.findByEmployeeNumber(employeeNumber);
        });
    }

    @Test
    void testFindAllEmployees() {
        // Given
        employeesService.saveEmployee(sampleEmployeeDTO);

        // When
        List<Employees> employeesList = employeesService.findAll();

        // Then
        assertNotNull(employeesList);
        assertFalse(employeesList.isEmpty());
        assertTrue(employeesList.stream().anyMatch(e -> "john_doe".equals(e.getUsername())));
    }
}