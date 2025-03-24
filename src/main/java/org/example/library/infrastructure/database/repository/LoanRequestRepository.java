package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.LoanRequestDao;
import org.example.library.domain.LoanRequest;
import org.example.library.infrastructure.database.entity.LoanRequestEntity;
import org.example.library.infrastructure.database.repository.jpa.LoanRequestJpaRepository;
import org.example.library.infrastructure.database.repository.mapper.LoanRequestEntityMapper;
import org.example.library.infrastructure.database.repository.mapper.LoanRequestEntityMapperClass;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class LoanRequestRepository implements LoanRequestDao {

    private final LoanRequestJpaRepository loanRequestJpaRepository;
    private final LoanRequestEntityMapperClass loanRequestEntityMapperClass;

    @Override
    public LoanRequest saveLoanRequestFromReservation(LoanRequest loanRequest) {
        LoanRequestEntity toSave = loanRequestEntityMapperClass.mapToLoanRequestEntity(loanRequest);
        LoanRequestEntity saved = loanRequestJpaRepository.save(toSave);
        return loanRequestEntityMapperClass.mapFromLoanRequestEntity(saved);
    }

    @Override
    public LoanRequest saveLoanRequestFromCart(LoanRequest loanRequest) {
        return null;
    }

    @Override
    public List<LoanRequest> findAll() {
        List<LoanRequestEntity> all = loanRequestJpaRepository.findAll();
        return all.stream().map(loanRequestEntityMapperClass::mapFromLoanRequestEntity).toList();
    }

    @Override
    public Optional<LoanRequest> findById(Integer loanRequestId) {
        return loanRequestJpaRepository.findById(loanRequestId).map(loanRequestEntityMapperClass::mapFromLoanRequestEntity);
    }

    @Override
    public List<LoanRequest> findByUserId(Integer userId) {
        return loanRequestJpaRepository.findByUserId(userId).stream().map(loanRequestEntityMapperClass::mapFromLoanRequestEntity).toList();
    }

    @Override
    public void deleteByCartUserId(Integer userId) {
        loanRequestJpaRepository.deleteByCartUserId(userId);
    }

    @Override
    public Optional<LoanRequest> findByLoanRequestNumber(String loanRequestNumber) {
        return loanRequestJpaRepository.findByLoanRequestNumber(loanRequestNumber).map(loanRequestEntityMapperClass::mapFromLoanRequestEntity);
    }

    @Override
    public void deleteByLoanRequestNumber(String loanRequestNumber) {
        loanRequestJpaRepository.deleteByLoanRequestNumber(loanRequestNumber);
    }
}
