package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.EmployeesDao;
import org.example.library.domain.Employees;
import org.example.library.infrastructure.database.entity.EmployeesEntity;
import org.example.library.infrastructure.database.repository.jpa.EmployeesJpaRepository;
import org.example.library.infrastructure.database.repository.mapper.EmployeesEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class EmployeesRepository implements EmployeesDao {

    private final EmployeesJpaRepository employeesJpaRepository;
    private final EmployeesEntityMapper employeesEntityMapper;


    @Override
    public Employees saveEmployee(Employees employee) {
        EmployeesEntity toSave = employeesEntityMapper.mapToEmployeesEntity(employee);
        EmployeesEntity saved = employeesJpaRepository.save(toSave);
        return employeesEntityMapper.mapFromEmployeesEntity(saved);
    }

    @Override
    public Optional<Employees> findByUsername(String username) {
        return employeesJpaRepository.findByUsername(username).map(employeesEntityMapper::mapFromEmployeesEntity);
    }

    @Override
    public List<Employees> findAll() {
        return employeesJpaRepository.findAll().stream().map(employeesEntityMapper::mapFromEmployeesEntity).collect(Collectors.toList());
    }

    @Override
    public Optional<Employees> findByEmployeeNumber(String employeeNumber) {
        return employeesJpaRepository.findByEmployeeNumber(employeeNumber).map(employeesEntityMapper::mapFromEmployeesEntity);
    }

    @Override
    public void deleteByEmployeeNumber(Employees byEmployeeNumber) {
        EmployeesEntity employeesEntity = employeesEntityMapper.mapToEmployeesEntity(byEmployeeNumber);
        employeesJpaRepository.delete(employeesEntity);
    }
}
