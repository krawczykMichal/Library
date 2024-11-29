package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.LoansDao;
import org.example.library.domain.Employees;
import org.example.library.domain.LoanRequest;
import org.example.library.domain.Loans;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoansService {

    private final LoansDao loansDao;


    public void makeLoan(List<LoanRequest> loanRequest, Employees employee) {
        Loans loan = Loans.builder()
                .employee(employee)
                .loanRequest().build()
    }
}
