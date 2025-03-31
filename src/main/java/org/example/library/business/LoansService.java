package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.BooksDao;
import org.example.library.business.dao.LoanItemDao;
import org.example.library.business.dao.LoansDao;
import org.example.library.domain.*;
import org.example.library.domain.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoansService {

    private final LoansDao loansDao;
    private final BooksDao booksDao;
    private final LoanItemDao loanItemDao;

    @Transactional
    public void makeLoan(LoanRequest loanRequest,List<LoanRequestItem> loanRequestItemList) {
        Loans loan = Loans.builder()
                .loanNumber(makeRandomLoanNumber())
                .user(loanRequest.getUser())
                .loanItem(makeLoanItemListFromLoanRequest(loanRequestItemList))
                .loanDate(LocalDateTime.now())
                .returned(false)
                .build();

        loansDao.save(loan);
    }

    private String makeRandomLoanNumber() {
            SecureRandom random = new SecureRandom();
            StringBuilder employeeNumber = new StringBuilder();

            for (int i = 0; i < 10; i++) {
                int digit = random.nextInt(10);
                employeeNumber.append(digit);
            }
            return employeeNumber.toString();

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

    public List<Loans> findAllForEmployee() {
        return loansDao.findAllForEmployee();
    }

    public Loans findByLoanNumber(String loanNumber) {
        Optional<Loans> loans = loansDao.findByLoanNumber(loanNumber);
        if (loans.isEmpty()) {
            throw new NotFoundException("Cannot find loan with loanNumber " + loanNumber);
        }
        return loans.get();
    }

    @Transactional
    public void returnLoan(String loanNumber) {
        Loans byLoanNumber = findByLoanNumber(loanNumber);
        System.out.println("loan: " + byLoanNumber);
        List<LoanItem> loanItems = loanItemDao.findByLoanNumber(loanNumber);
        System.out.println("loanItems: " + loanItems);
        for (LoanItem loanItem : loanItems) {
            Books books = loanItem.getBook().withCopies(loanItem.getBook().getCopies() + loanItem.getQuantity());
            booksDao.saveBook(books);
        }
        loansDao.returnLoan(loanNumber);
    }
}
