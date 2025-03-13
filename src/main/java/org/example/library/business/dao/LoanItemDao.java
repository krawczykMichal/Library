package org.example.library.business.dao;

import org.example.library.domain.LoanItem;

import java.util.List;

public interface LoanItemDao {

    List<LoanItem> findAllByLoanId(Integer loanId);

    void deleteByLoanUserId(Integer userId);

    void deleteByReservationId(Integer reservationId);

    List<LoanItem> findByLoanNumber(String loanNumber);
}
