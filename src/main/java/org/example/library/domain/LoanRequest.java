package org.example.library.domain;

import lombok.*;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = "loanRequestId")
@ToString(of = {"loanRequestId"})
public class LoanRequest {

    Integer loanRequestId;
    String loanRequestNumber;
    Reservations reservation;
    LocalDateTime requestDate;
    Cart cart;
    Users user;
    List<LoanRequestItem> loanRequestItems;
}
