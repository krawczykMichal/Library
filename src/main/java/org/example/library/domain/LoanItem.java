package org.example.library.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "loanItemId")
@ToString(of = {"loanItemId", "book", "quantity"})
public class LoanItem {

    Integer loanItemId;
    String title;
    Integer quantity;
    Books book;
}
