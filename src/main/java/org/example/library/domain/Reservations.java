package org.example.library.domain;

import lombok.*;

import java.time.LocalDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "reservationId")
@ToString(of = {"reservationId", "reservationDate"})
public class Reservations {

    Integer reservationId;
    LocalDateTime reservationDate;
    Cart cart;
}
