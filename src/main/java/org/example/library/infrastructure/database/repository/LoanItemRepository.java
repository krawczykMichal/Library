package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.LoanItemDao;
import org.example.library.domain.LoanItem;
import org.example.library.infrastructure.database.repository.jpa.LoanItemJpaRepository;
import org.example.library.infrastructure.database.repository.mapper.LoanItemEntityMapper;
import org.example.library.infrastructure.database.repository.mapper.LoanItemEntityMapperClass;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class LoanItemRepository implements LoanItemDao {

    private final LoanItemJpaRepository loanItemJpaRepository;
    private final LoanItemEntityMapper loanItemEntityMapper;
    private final LoanItemEntityMapperClass loanItemEntityMapperClass;

    @Override
    public List<LoanItem> findAllByLoanId(Integer loanId) {
        return loanItemJpaRepository.findAllByLoanId(loanId).stream().map(loanItemEntityMapperClass::mapFromLoanItemEntity).toList();
    }

    @Override
    public void deleteByLoanUserId(Integer userId) {
        loanItemJpaRepository.deleteByLoanUserId(userId);
    }


    @Override
    public List<LoanItem> findByLoanNumber(String loanNumber) {
        return loanItemJpaRepository.findByLoanNumber(loanNumber).stream().map(loanItemEntityMapperClass::mapFromLoanItemEntity).toList();
    }
}
