package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.api.dto.EmployeesDTO;
import org.example.library.business.dao.EmployeesDao;
import org.example.library.domain.Employees;
import org.example.library.domain.exception.NotFoundException;
import org.example.library.domain.exception.UserNameAlreadyTakenException;
import org.example.library.domain.exception.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class EmployeesService {

    private final EmployeesDao employeesDao;

    @Transactional
    public void saveEmployee(EmployeesDTO employeesDTO) {
        Employees employee = Employees.builder()
                .name(employeesDTO.getName())
                .surname(employeesDTO.getSurname())
                .username(employeesDTO.getUsername())
                .email(employeesDTO.getEmail())
                .employeeNumber(createEmployeeNumber())
                .build();

        employeesDao.saveEmployee(employee);
    }

    private String createEmployeeNumber() {
        SecureRandom random = new SecureRandom();
        StringBuilder employeeNumber = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            employeeNumber.append(digit);
        }
        return employeeNumber.toString();
    }

    public Employees findByUsername(String username) {
        Optional<Employees> employee = employeesDao.findByUsername(username);
        if (employee.isEmpty()) {
            throw new NotFoundException("Could not find employee with username: " + username);
        }
        return employee.get();
    }


    public List<Employees> findAll() {
        return employeesDao.findAll();
    }

    public Employees findByEmployeeNumber(String employeeNumber) {
        Optional<Employees> employees = employeesDao.findByEmployeeNumber(employeeNumber);
        if (employees.isEmpty()) {
            throw new NotFoundException("Could not find employee with employeeNumber: " + employeeNumber);
        }

        return employees.get();
    }

    @Transactional
    public void updateEmployee(String employeeNumber, EmployeesDTO employeesDTO) {
        Employees byEmployeeNumber = findByEmployeeNumber(employeeNumber);

        if (!byEmployeeNumber.getUsername().equals(employeesDTO.getUsername()) && employeesDao.findByUsername(employeesDTO.getUsername()).isPresent()) {
            throw new UserNameAlreadyTakenException("Username '" + employeesDTO.getUsername() + "' is already taken.");
        }

        validateEmployeeData(employeesDTO);

        Employees employee = byEmployeeNumber.withName(employeesDTO.getName() != null && !employeesDTO.getName().isEmpty() ? employeesDTO.getName() : byEmployeeNumber.getName())
                .withSurname(employeesDTO.getSurname() != null && !employeesDTO.getSurname().isEmpty() ? employeesDTO.getSurname() : byEmployeeNumber.getSurname())
                .withUsername(employeesDTO.getUsername() != null && !employeesDTO.getUsername().isEmpty() ? employeesDTO.getUsername() : byEmployeeNumber.getUsername())
                .withEmail(employeesDTO.getEmail() != null && !employeesDTO.getEmail().isEmpty() ? employeesDTO.getEmail() : byEmployeeNumber.getEmail());

        employeesDao.saveEmployee(employee);
    }

    private void validateEmployeeData(EmployeesDTO employeesDTO) {
        Pattern namePattern = Pattern.compile("^[A-Za-zĄąĆćĘęŁłŃńÓóŚśŹźŻż]+$");
        Pattern usernamePattern = Pattern.compile("^[A-Za-zĄąĆćĘęŁłŃńÓóŚśŹźŻż0-9._]+$");
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

        if (employeesDTO.getName() != null && !employeesDTO.getName().isEmpty() && !namePattern.matcher(employeesDTO.getName()).matches()) {
            throw new ValidationException("Name can only contain letters (including Polish characters)");
        }
        if (employeesDTO.getSurname() != null && !employeesDTO.getSurname().isEmpty() && !namePattern.matcher(employeesDTO.getSurname()).matches()) {
            throw new ValidationException("Last name can only contain letters (including Polish characters)");
        }
        if (employeesDTO.getUsername() != null && !employeesDTO.getUsername().isEmpty() && !usernamePattern.matcher(employeesDTO.getUsername()).matches()) {
            throw new ValidationException("Username can only contain letters, numbers, dots, and underscores");
        }
        if (employeesDTO.getEmail() != null && !employeesDTO.getEmail().isEmpty() && !emailPattern.matcher(employeesDTO.getEmail()).matches()) {
            throw new ValidationException("Email must be valid (e.g., name@email.com");
        }
    }

    @Transactional
    public void deleteByEmployeeNumber(String employeeNumber) {
        Employees byEmployeeNumber = findByEmployeeNumber(employeeNumber);
        employeesDao.deleteByEmployeeNumber(byEmployeeNumber);
    }
}
