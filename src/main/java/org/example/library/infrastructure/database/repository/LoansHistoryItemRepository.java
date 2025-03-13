package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.LoansHistoryItemDao;
import org.example.library.infrastructure.database.repository.jpa.LoansHistoryJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class LoansHistoryItemRepository implements LoansHistoryItemDao {
}
