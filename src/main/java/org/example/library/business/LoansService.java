package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.LoansDao;
import org.example.library.domain.*;
import org.example.library.domain.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoansService {

    private final LoansDao loansDao;

    @Transactional
    public void makeLoan(LoanRequest loanRequest,List<LoanRequestItem> loanRequestItemList, Employees employee, Users user) {
        Loans loan = Loans.builder()
                .employee(employee)
                .user(user)
                .loanRequest(loanRequest)
                .loanItem(makeLoanItemListFromLoanRequest(loanRequestItemList))
                .loanDate(LocalDateTime.now())
                .build();

        loansDao.save(loan);
    }

    private List<LoanItem> makeLoanItemListFromLoanRequest(List<LoanRequestItem> loanRequestItemList) {
        List<LoanItem> loanItemList = new ArrayList<>();

        for (LoanRequestItem loanRequestItem : loanRequestItemList) {
            LoanItem loanItem = LoanItem.builder()
                    .title(loanRequestItem.getTitle())
                    .book(loanRequestItem.getBook())
                    .quantity(loanRequestItem.getQuantity())
                    .build();

            loanItemList.add(loanItem);
        }

        return loanItemList;

    }

    public List<Loans> findAllByUserId(Integer userId) {
        return loansDao.findAllByUserId(userId);
    }
    public List<Loans> findAllByUserId(Integer userId, boolean returned) {
        return loansDao.findAllByUserId(userId, returned);
    }

    public Loans findById(Integer loanId) {
        Optional<Loans> loans = loansDao.findById(loanId);
        if (loans.isEmpty()) {
           throw new NotFoundException("Cannot find loan with id " + loanId);
        }
        return loans.get();
    }

    public List<Loans> findAll() {
        return loansDao.findAll();
    }

    public Loans findByLoanNumber(String loanNumber) {
        Optional<Loans> loans = loansDao.findByLoanNumber(loanNumber);
        if (loans.isEmpty()) {
            throw new NotFoundException("Cannot find loan with loanNumber " + loanNumber);
        }
        return loans.get();
    }
}
