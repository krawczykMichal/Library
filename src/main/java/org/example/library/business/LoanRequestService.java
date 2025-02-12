package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.LoanRequestDao;
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
public class LoanRequestService {

    private final LoanRequestDao loanRequestDao;

    @Transactional
    public void makeLoanRequestFromReservation(Reservations reservation, List<ReservationItem> reservationItemList) {
        LoanRequest loanRequest = LoanRequest.builder()
                .loanRequestNumber(createLoanRequestNumber())
                .reservation(reservation)
                .loanRequestItems(makeLoamRequestItemListFromReservation(reservationItemList))
                .requestDate(LocalDateTime.now())
                .build();
        loanRequestDao.saveLoanRequest(loanRequest);
    }

    private String createLoanRequestNumber() {
        SecureRandom random = new SecureRandom();
        StringBuilder employeeNumber = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            employeeNumber.append(digit);
        }
        return employeeNumber.toString();
    }

    private List<LoanRequestItem> makeLoamRequestItemListFromReservation(List<ReservationItem> reservationItemList) {
        List<LoanRequestItem> loanRequestItemList = new ArrayList<>();

        for (ReservationItem reservationItem : reservationItemList) {
            LoanRequestItem loanRequestItem = LoanRequestItem.builder()
                    .title(reservationItem.getTitle())
                    .book(reservationItem.getBook())
                    .quantity(reservationItem.getQuantity())
                    .build();

            loanRequestItemList.add(loanRequestItem);
        }

        return loanRequestItemList;

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
    public void makeLoanRequestFromCart(Cart cart, List<CartItem> cartItemList) {
        LoanRequest loanRequest = LoanRequest.builder()
                .cart(cart)
                .loanRequestItems(makeLoamRequestItemListFromCart(cartItemList))
                .requestDate(LocalDateTime.now())
                .build();
        loanRequestDao.saveLoanRequest(loanRequest);

    }

    private List<LoanRequestItem> makeLoamRequestItemListFromCart(List<CartItem> cartItems) {
        List<LoanRequestItem> loanRequestItemList = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            LoanRequestItem loanRequestItem = LoanRequestItem.builder()
                    .title(cartItem.getTitle())
                    .book(cartItem.getBook())
                    .quantity(cartItem.getQuantity())
                    .build();

            loanRequestItemList.add(loanRequestItem);
        }

        return loanRequestItemList;

    }

    public List<LoanRequest> findByUserId(Integer userId) {
        return loanRequestDao.findByUserId(userId);
    }
}
