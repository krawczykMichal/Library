package org.example.library.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = "reservationId")
@ToString(of = {"reservationId", "reservationMakeDate", "reservationHoldToDate"})
public class Reservations {

    Integer reservationId;
    String reservationNumber;
    LocalDateTime reservationMakeDate;
    LocalDateTime reservationHoldToDate;
    Cart cart;
    List<ReservationItem> reservationItem;
}

