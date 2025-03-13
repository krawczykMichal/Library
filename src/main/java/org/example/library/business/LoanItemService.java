package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.LoanItemDao;
import org.example.library.domain.LoanItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoanItemService {

    private final LoanItemDao loanItemDao;
    public List<LoanItem> findAllByLoanId(Integer loanId) {
        return loanItemDao.findAllByLoanId(loanId);
    }

    public List<LoanItem> findByLoanNumber(String loanNumber) {
        return loanItemDao.findByLoanNumber(loanNumber);
    }
}
