package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.LoanRequestDao;
import org.example.library.domain.LoanRequest;
import org.example.library.infrastructure.database.entity.LoanRequestEntity;
import org.example.library.infrastructure.database.repository.jpa.LoanRequestJpaRepository;
import org.example.library.infrastructure.database.repository.mapper.LoanRequestEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class LoanRequestRepository implements LoanRequestDao {

    private final LoanRequestJpaRepository loanRequestJpaRepository;
    private final LoanRequestEntityMapper loanRequestEntityMapper;

    @Override
    public LoanRequest saveLoanRequest(LoanRequest loanRequest) {
        LoanRequestEntity toSave = loanRequestEntityMapper.mapToLoanRequestEntity(loanRequest);
        LoanRequestEntity saved = loanRequestJpaRepository.save(toSave);
        return loanRequestEntityMapper.mapFromLoanRequestEntity(saved);
    }

    @Override
    public List<LoanRequest> findAll() {
        List<LoanRequestEntity> all = loanRequestJpaRepository.findAll();
        return all.stream().map(loanRequestEntityMapper::mapFromLoanRequestEntity).toList();
    }
}
