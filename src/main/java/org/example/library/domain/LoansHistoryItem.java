package org.example.library.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "loansHistoryItemId")
@ToString(of = {"loansHistoryItemId", "book", "quantity"})
public class LoansHistoryItem {

    Integer loansHistoryItemId;
    String title;
    Integer quantity;
    Books book;
}
