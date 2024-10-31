package org.example.library.infrastructure.database.repository.jpa;

import org.example.library.infrastructure.database.entity.EmployeesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesJpaRepository extends JpaRepository<EmployeesEntity, Integer> {
}
