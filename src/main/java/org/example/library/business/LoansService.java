package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.LoansDao;
import org.example.library.domain.Employees;
import org.example.library.domain.LoanRequest;
import org.example.library.domain.Loans;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class LoansService {

    private final LoansDao loansDao;

    @Transactional
    public void makeLoan(LoanRequest loanRequest, Employees employee) {
        Loans loan = Loans.builder()
                .employee(employee)
                .loanRequest(loanRequest)
                .loanDate(LocalDateTime.now())
                .build();

        loansDao.save(loan);
    }
}
