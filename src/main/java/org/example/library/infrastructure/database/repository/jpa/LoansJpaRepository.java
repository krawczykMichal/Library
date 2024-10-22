package org.example.library.infrastructure.database.repository.jpa;

import org.example.library.infrastructure.database.entity.LoansEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoansJpaRepository extends JpaRepository<LoansEntity, Integer> {
}
