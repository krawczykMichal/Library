package org.example.library.domain;

import lombok.*;

import java.awt.print.Book;
import java.time.LocalDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "loanRequestId")
@ToString(of = {"loanRequestId"})
public class LoanRequest {

    Integer loanRequestId;
    LoanRequestItem loanRequestItem;
    LocalDateTime requestDate;
}
