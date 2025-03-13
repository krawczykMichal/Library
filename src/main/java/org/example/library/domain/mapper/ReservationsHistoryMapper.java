package org.example.library.domain.mapper;

import org.example.library.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationsHistoryMapper {


    public ReservationsHistory mapFromReservations(Reservations reservations) {
        if (reservations == null) {
            return null;
        }

        return ReservationsHistory.builder()
                .reservationNumber(reservations.getReservationNumber())
                .reservationMakeDate(reservations.getReservationMakeDate())
                .reservationHoldToDate(reservations.getReservationHoldToDate())
                .reservationHistoryItems(mapFromReservationsItem(reservations.getReservationItem()))
                .build();
    }

    private List<ReservationsHistoryItem> mapFromReservationsItem(List<ReservationItem> reservationItem) {
        if (reservationItem == null) {
            return null;

        }

        return reservationItem.stream()
                        .map(entities -> ReservationsHistoryItem.builder()
                                .title(entities.getTitle())
                                .book(entities.getBook())
                                .quantity(entities.getQuantity())
                                .build()).toList();
    }

}
