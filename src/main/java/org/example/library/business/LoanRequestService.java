package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.LoanRequestDao;
import org.example.library.business.dao.LoanRequestItemDao;
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
    private final LoanRequestItemDao loanRequestItemDao;
    private final LoanRequestItemService loanRequestItemService;

    @Transactional
    public void makeLoanRequestFromReservation(Reservations reservation, Users userByUsername) {
        List<ReservationItem> reservationItemList = reservation.getReservationItem();
        System.out.println("reservationItemList: " + reservationItemList);
        LoanRequest loanRequest = LoanRequest.builder()
                .loanRequestNumber(reservation.getReservationNumber())
                .requestDate(LocalDateTime.now())
                .build();
        LoanRequest loanRequest1 = loanRequest.withUser(userByUsername);
        LoanRequest loanRequest2 = loanRequestDao.saveLoanRequestFromReservation(loanRequest1);

        loanRequestItemService.addItems(reservationItemList, loanRequest2);
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
        Users user = cart.getUser();
        LoanRequest loanRequest = LoanRequest.builder()
                .loanRequestItems(makeLoamRequestItemListFromCart(cartItemList))
                .requestDate(LocalDateTime.now())
                .build();
        LoanRequest loanRequest1 = loanRequest.withUser(user);
        loanRequestDao.saveLoanRequestFromCart(loanRequest1);

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

    public LoanRequest findByLoanRequestNumber(String loanRequestNumber) {
        Optional<LoanRequest> loanRequest = loanRequestDao.findByLoanRequestNumber(loanRequestNumber);
        if (loanRequest.isEmpty()) {
            throw new NotFoundException("Could not find loan request with loanRequestNumber: " + loanRequestNumber);
        }
        return loanRequest.get();
    }

    @Transactional
    public void deleteLoanRequest(LoanRequest loanRequest) {
        loanRequestItemDao.deleteByLoanRequestId(loanRequest.getLoanRequestId());
        loanRequestDao.deleteByLoanRequestNumber(loanRequest.getLoanRequestNumber());
    }

}
