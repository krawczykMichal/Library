package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.LoanRequestItemDao;
import org.example.library.infrastructure.database.repository.jpa.LoanRequestItemJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class LoanRequestItemRepository implements LoanRequestItemDao {

    private final LoanRequestItemJpaRepository loanRequestItemJpaRepository;

    @Override
    public void deleteByLoanRequestCartUserId(Integer userId) {
        loanRequestItemJpaRepository.deleteByLoanRequestCartUserId(userId);
    }
}
