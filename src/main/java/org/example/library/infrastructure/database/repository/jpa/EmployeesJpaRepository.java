package org.example.library.infrastructure.database.repository.jpa;

import org.example.library.infrastructure.database.entity.EmployeesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeesJpaRepository extends JpaRepository<EmployeesEntity, Integer> {

    @Query("select emp from EmployeesEntity emp where emp.username = :username")
    Optional<EmployeesEntity> findByUsername(String username);

    @Query("select emp from EmployeesEntity emp where emp.employeeNumber = :employeeNumber")
    Optional<EmployeesEntity> findByEmployeeNumber(String employeeNumber);
}
