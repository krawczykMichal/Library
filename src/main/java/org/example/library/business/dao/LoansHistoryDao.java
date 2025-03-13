package org.example.library.business.dao;

import org.example.library.domain.LoansHistory;

import java.util.List;
import java.util.Optional;

public interface LoansHistoryDao {


    List<LoansHistory> findAllByUserId(Integer userId);

    Optional<LoansHistory> findByLoanNumber(String loanNumber);
}
