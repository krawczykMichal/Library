package org.example.library.business.dao;

import org.example.library.domain.ReservationItem;

import java.util.List;

public interface ReservationItemDao {
    void deleteByReservationCartUserId(Integer userId);

    void deleteByReservationNumber(String reservationNumber);

    void saveAll(List<ReservationItem> reservationItemList);

    ReservationItem save(ReservationItem reservationItem);
}
