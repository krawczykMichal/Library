package org.example.library.business.dao;

import org.example.library.domain.LoanRequest;

import java.util.List;

public interface LoanRequestDao {

    LoanRequest saveLoanRequest(LoanRequest loanRequest);

    List<LoanRequest> findAll();
}
