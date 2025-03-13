package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.LoansHistoryDao;
import org.example.library.domain.LoansHistory;
import org.example.library.infrastructure.database.repository.jpa.LoansHistoryJpaRepository;
import org.example.library.infrastructure.database.repository.mapper.LoansHistoryEntityMapperClass;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class LoansHistoryRepository implements LoansHistoryDao {

    private final LoansHistoryJpaRepository loansHistoryJpaRepository;
    private final LoansHistoryEntityMapperClass loansHistoryEntityMapperClass;

    @Override
    public List<LoansHistory> findAllByUserId(Integer userId) {
        return loansHistoryJpaRepository.findAllByUserId(userId).stream().map(loansHistoryEntityMapperClass::mapFromLoansHistoryEntity).toList();
    }

    @Override
    public Optional<LoansHistory> findByLoanNumber(String loanNumber) {
        return loansHistoryJpaRepository.findByLoanNumber(loanNumber).map(loansHistoryEntityMapperClass::mapFromLoansHistoryEntity);
    }
}
