package org.example.library.business.dao;

import org.example.library.domain.LoanRequest;

import java.util.List;
import java.util.Optional;

public interface LoanRequestDao {

    LoanRequest saveLoanRequestFromReservation(LoanRequest loanRequest);

    LoanRequest saveLoanRequestFromCart(LoanRequest loanRequest);

    List<LoanRequest> findAll();

    Optional<LoanRequest> findById(Integer loanRequestId);

    List<LoanRequest> findByUserId(Integer userId);

    void deleteByCartUserId(Integer userId);

    Optional<LoanRequest> findByLoanRequestNumber(String loanRequestNumber);

    void deleteByLoanRequestNumber(String loanRequestNumber);

    void deleteByReservationId(Integer reservationId);
}
