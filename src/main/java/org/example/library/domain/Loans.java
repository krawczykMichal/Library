package org.example.library.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = "loanId")
@ToString(of = {"loanId", "loanNumber", "loanDate", "returnDate", "returned"})
public class Loans {

    Integer loanId;
    String loanNumber;
    LocalDateTime loanDate;
    LocalDateTime returnDate;
    Boolean returned;
    LoanRequest loanRequest;
    Employees employee;
    Users user;
    List<LoanItem> loanItem;
}
