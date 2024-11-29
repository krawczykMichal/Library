package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.LoanRequestDao;
import org.example.library.domain.LoanRequest;
import org.example.library.domain.Reservations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class LoanRequestService {

    private final LoanRequestDao loanRequestDao;

    @Transactional
    public void loanRequest(Reservations reservation) {
        LoanRequest loanRequest = LoanRequest.builder()
                .reservations(reservation)
                .requestDate(LocalDateTime.now())
                .build();
        loanRequestDao.saveLoanRequest(loanRequest);
    }

    public List<LoanRequest> findAll() {
        return loanRequestDao.findAll();
    }
}
