package org.example.library.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "reservationItemId")
@ToString(of = {"reservationItemId", "book", "quantity"})
public class ReservationItem {

    Integer reservationItemId;
    String title;
    Integer quantity;
    Books book;
    Reservations reservation;
}
