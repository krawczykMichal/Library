package org.example.library.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "loanRequestItemId")
@ToString(of = {"loanRequestItemId", "book", "quantity"})
public class LoanRequestItem {

    Integer loanRequestItemId;
    String title;
    Integer quantity;
    Books book;
}
