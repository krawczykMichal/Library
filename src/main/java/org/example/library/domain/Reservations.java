package org.example.library.domain;

import lombok.*;

import java.time.LocalDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "reservationId")
@ToString(of = {"reservationId", "reservationMakeDate", "reservationHoldToDate"})
public class Reservations {

    Integer reservationId;
    LocalDateTime reservationMakeDate;
    LocalDateTime reservationHoldToDate;
    Cart cart;
}

