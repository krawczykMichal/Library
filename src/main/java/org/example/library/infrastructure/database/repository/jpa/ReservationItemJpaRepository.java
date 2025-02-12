package org.example.library.infrastructure.database.repository.jpa;

import org.example.library.infrastructure.database.entity.ReservationItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationItemJpaRepository extends JpaRepository<ReservationItemEntity, Integer> {

    @Modifying
    @Query("delete from ReservationItemEntity res where res.reservation.cart.user.userId = :userId")
    void deleteByReservationCartUserId(Integer userId);
}
