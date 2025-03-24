package org.example.library.business.dao;

import org.example.library.domain.Loans;

import java.util.List;
import java.util.Optional;

public interface LoansDao {

    Loans save(Loans loan);

    List<Loans> findAllByUserId(Integer userId);

    List<Loans> findAllByUserId(Integer userId, boolean returned);

    Optional<Loans> findById(Integer loanId);

    List<Loans> findAllForEmployee();

    Optional<Loans> findByLoanNumber(String loanNumber);

    void deleteByUserId(Integer userId);

    int returnLoan(String loanNumber);

}
