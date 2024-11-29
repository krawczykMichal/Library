package org.example.library.business.dao;

import org.example.library.domain.Employees;

import java.util.Optional;

public interface EmployeesDao {

    Employees saveEmployee(Employees employee);

    Optional<Employees> findByUsername(String username);
}
