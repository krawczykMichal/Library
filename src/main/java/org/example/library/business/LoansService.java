package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.LoansDao;
import org.example.library.domain.Cart;
import org.example.library.domain.Employees;
import org.example.library.domain.LoanRequest;
import org.example.library.domain.Loans;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public List<Loans> findAllByUserId(Integer userId) {
        return loansDao.findAllByUserId(userId);
    }
    public List<Loans> findAllByUserId(Integer userId, boolean returned) {
        return loansDao.findAllByUserId(userId, returned);
    }

    public Loans findById(Integer loanId) {
        Optional<Loans> loans = loansDao.findById(loanId);
        return loans.get();
    }

    public List<Loans> findAll() {
        return loansDao.findAll();
    }
}
