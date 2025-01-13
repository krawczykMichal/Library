package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.LoanRequestDao;
import org.example.library.domain.Cart;
import org.example.library.domain.LoanRequest;
import org.example.library.domain.Reservations;
import org.example.library.domain.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoanRequestService {

    private final LoanRequestDao loanRequestDao;

    @Transactional
    public void loanRequest(Reservations reservation) {
        LoanRequest loanRequest = LoanRequest.builder()
                .reservation(reservation)
                .requestDate(LocalDateTime.now())
                .build();
        loanRequestDao.saveLoanRequest(loanRequest);
    }

    public List<LoanRequest> findAll() {
        return loanRequestDao.findAll();
    }

    @Transactional
    public LoanRequest findById(Integer loanRequestId) {
        Optional<LoanRequest> loanRequest = loanRequestDao.findById(loanRequestId);
        if (loanRequest.isEmpty()) {
            throw new NotFoundException("Could not find loan request with id: " + loanRequestId);
        }
        return loanRequest.get();
    }

    @Transactional
    public void makeLoanRequestFromCart(Cart cart) {
        LoanRequest loanRequest = LoanRequest.builder()
                .cart(cart)
                .requestDate(LocalDateTime.now())
                .build();
        loanRequestDao.saveLoanRequest(loanRequest);

    }
}
