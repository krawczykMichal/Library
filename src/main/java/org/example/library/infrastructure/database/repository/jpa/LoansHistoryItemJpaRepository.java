package org.example.library.infrastructure.database.repository.jpa;

import org.example.library.infrastructure.database.entity.LoansHistoryItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoansHistoryItemJpaRepository extends JpaRepository<LoansHistoryItemEntity, Integer> {
}
