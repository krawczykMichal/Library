package org.example.library.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.library.business.dao.LoanRequestItemDao;
import org.example.library.domain.LoanRequestItem;
import org.example.library.domain.exception.NotFoundException;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@AllArgsConstructor
public class LoanRequestItemService {

    private final LoanRequestItemDao loanRequestItemDao;

    public List<LoanRequestItem> findByLoanRequestNumber(String loanRequestNumber) {

        return loanRequestItemDao.findByLoanRequestNumber(loanRequestNumber);
    }

}
