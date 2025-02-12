package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.ReservationItemDao;
import org.example.library.infrastructure.database.repository.jpa.ReservationItemJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ReservationItemRepository implements ReservationItemDao {

    private final ReservationItemJpaRepository reservationItemJpaRepository;

    @Override
    public void deleteByReservationCartUserId(Integer userId) {
        reservationItemJpaRepository.deleteByReservationCartUserId(userId);
    }
}
