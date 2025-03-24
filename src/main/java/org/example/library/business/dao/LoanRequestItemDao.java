package org.example.library.business.dao;

import org.example.library.domain.LoanRequestItem;

import java.util.List;
import java.util.Optional;

public interface LoanRequestItemDao {
    void deleteByLoanRequestCartUserId(Integer userId);

    void deleteByLoanRequestId(Integer loanRequestId);

    List<LoanRequestItem> findByLoanRequestNumber(String loanRequestNumber);

    LoanRequestItem save(LoanRequestItem loanRequestItem);
}
