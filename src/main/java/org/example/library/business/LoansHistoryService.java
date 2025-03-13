package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.LoansHistoryDao;
import org.example.library.domain.LoansHistory;
import org.example.library.domain.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoansHistoryService {

    private final LoansHistoryDao loansHistoryDao;

    public List<LoansHistory> findAllByUserId(Integer userId) {
        return loansHistoryDao.findAllByUserId(userId);
    }

    public LoansHistory findByLoanNumber(String loanNumber) {
        Optional<LoansHistory> loansHistoryOptional = loansHistoryDao.findByLoanNumber(loanNumber);
        if (loansHistoryOptional.isEmpty()) {
            throw new NotFoundException("LoansHistory with loanNumber " + loanNumber + " not found");
        }
        return loansHistoryOptional.get();
    }
}
