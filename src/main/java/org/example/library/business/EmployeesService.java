package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.api.dto.EmployeesDTO;
import org.example.library.business.dao.EmployeesDao;
import org.example.library.domain.Employees;
import org.example.library.domain.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Optional;

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
}
