package org.example.library.business.dao;

public interface ReservationItemDao {
    void deleteByReservationCartUserId(Integer userId);

    void deleteByReservationNumber(String reservationNumber);
}
