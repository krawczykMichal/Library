package org.example.library.domain;

import lombok.*;

import java.time.LocalDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "loanId")
@ToString(of = {"loanId", "loanDate", "returnDate", "returned"})
public class Loans {

    Integer loanId;
    LocalDateTime loanDate;
    LocalDateTime returnDate;
    Boolean returned;
    Users users;
    Books book;
}
