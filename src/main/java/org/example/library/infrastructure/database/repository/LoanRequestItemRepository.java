package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.LoanRequestItemDao;
import org.example.library.domain.LoanRequestItem;
import org.example.library.infrastructure.database.repository.jpa.LoanRequestItemJpaRepository;
import org.example.library.infrastructure.database.repository.mapper.LoanRequestItemEntityMapper;
import org.example.library.infrastructure.database.repository.mapper.LoanRequestItemEntityMapperClass;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class LoanRequestItemRepository implements LoanRequestItemDao {

    private final LoanRequestItemJpaRepository loanRequestItemJpaRepository;
    private final LoanRequestItemEntityMapperClass loanRequestItemEntityMapperClass;

    @Override
    public void deleteByLoanRequestCartUserId(Integer userId) {
        loanRequestItemJpaRepository.deleteByLoanRequestCartUserId(userId);
    }

    @Override
    public void deleteByLoanRequestId(Integer loanRequestId) {
        loanRequestItemJpaRepository.deleteByLoanRequestId(loanRequestId);
    }

    @Override
    public List<LoanRequestItem> findByLoanRequestNumber(String loanRequestNumber) {
        return loanRequestItemJpaRepository.findByLoanRequestNumber(loanRequestNumber).stream().map(loanRequestItemEntityMapperClass::mapFromLoanRequestItemEntity).toList();
    }

    @Override
    public void deleteByReservationId(Integer reservationId) {
        loanRequestItemJpaRepository.deleteByReservationId(reservationId);
    }
}
