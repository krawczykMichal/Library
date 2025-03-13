package org.example.library.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "reservationHistoryItemId")
@ToString(of = {"reservationHistoryItemId", "title", "book", "quantity", "reservationHistory"})
public class ReservationsHistoryItem {

    Integer reservationHistoryItemId;
    String title;
    Integer quantity;
    ReservationsHistory reservationHistory;
    Books book;
}
