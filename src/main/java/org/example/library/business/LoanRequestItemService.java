package org.example.library.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.library.business.dao.LoanRequestItemDao;
import org.example.library.domain.LoanRequest;
import org.example.library.domain.LoanRequestItem;
import org.example.library.domain.ReservationItem;
import org.example.library.domain.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Transactional
    public void addItems(List<ReservationItem> reservationItemList, LoanRequest loanRequest) {

        for (ReservationItem reservationItem : reservationItemList) {
            LoanRequestItem loanRequestItem = LoanRequestItem.builder()
                    .title(reservationItem.getTitle())
                    .book(reservationItem.getBook())
                    .quantity(reservationItem.getQuantity())
                    .loanRequest(loanRequest)
                    .build();

            loanRequestItemDao.save(loanRequestItem);
        }

    }
}
