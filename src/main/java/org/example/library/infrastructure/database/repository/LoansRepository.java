package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.LoansDao;
import org.example.library.domain.Loans;
import org.example.library.infrastructure.database.entity.LoansEntity;
import org.example.library.infrastructure.database.repository.jpa.LoanRequestJpaRepository;
import org.example.library.infrastructure.database.repository.jpa.LoansJpaRepository;
import org.example.library.infrastructure.database.repository.mapper.LoansEntityMapper;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class LoansRepository implements LoansDao {

    private final LoansJpaRepository loansJpaRepository;
    private final LoansEntityMapper loansEntityMapper;

    @Override
    public Loans save(Loans loan) {
        LoansEntity toSave = loansEntityMapper.mapToLoansEntity(loan);
        LoansEntity saved = loansJpaRepository.save(toSave);
        return loansEntityMapper.mapFromLoansEntity(saved);
    }
}
