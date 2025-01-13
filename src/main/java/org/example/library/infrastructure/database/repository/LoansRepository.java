package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.LoansDao;
import org.example.library.domain.Loans;
import org.example.library.infrastructure.database.entity.LoansEntity;
import org.example.library.infrastructure.database.repository.jpa.LoanRequestJpaRepository;
import org.example.library.infrastructure.database.repository.jpa.LoansJpaRepository;
import org.example.library.infrastructure.database.repository.mapper.LoansEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<Loans> findAllByUserId(Integer userId) {
        List<LoansEntity> all = loansJpaRepository.findAll();
        return all.stream().map(loansEntityMapper::mapFromLoansEntity).collect(Collectors.toList());
    }

    @Override
    public List<Loans> findAllByUserId(Integer userId, boolean returned) {
        List<LoansEntity> all = loansJpaRepository.findAllWithReturned(userId, returned);
        return all.stream().map(loansEntityMapper::mapFromLoansEntity).collect(Collectors.toList());
    }

    @Override
    public Optional<Loans> findById(Integer loanId) {
        Optional<LoansEntity> loansEntity = loansJpaRepository.findById(loanId);
        return loansEntity.map(loansEntityMapper::mapFromLoansEntity);
    }
}
