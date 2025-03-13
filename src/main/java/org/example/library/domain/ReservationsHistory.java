package org.example.library.domain;

import lombok.*;
import org.example.library.infrastructure.database.entity.ReservationsHistoryItemEntity;

import java.time.LocalDateTime;
import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = "reservationHistoryId")
@ToString(of = {"reservationHistoryId", "reservationNumber", "reservationMakeDate", "reservationHoldToDate"})
public class ReservationsHistory {

    Integer reservationHistoryId;
    String reservationNumber;
    LocalDateTime reservationMakeDate;
    LocalDateTime reservationHoldToDate;
    List<ReservationsHistoryItem> reservationHistoryItems;
    Users user;
}
