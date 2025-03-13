package org.example.library.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = "loanHistoryId")
@ToString(of = {"loanHistoryId", "loanNumber", "loanDate", "returnDate"})
public class LoansHistory {

    Integer loanHistoryId;
    String loanNumber;
    LocalDateTime loanDate;
    LocalDateTime returnDate;
    Employees employee;
    Users user;
    List<LoansHistoryItem> loansHistoryItems;
}
