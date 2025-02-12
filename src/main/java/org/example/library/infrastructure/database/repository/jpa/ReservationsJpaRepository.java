package org.example.library.infrastructure.database.repository.jpa;

import aj.org.objectweb.asm.commons.Remapper;
import org.example.library.domain.Reservations;
import org.example.library.infrastructure.database.entity.ReservationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationsJpaRepository extends JpaRepository<ReservationsEntity, Integer> {

    @Query("select res from ReservationsEntity res where res.cart.user.userId = :userId")
    List<ReservationsEntity> findByUserId(Integer userId);

    @Query("select res from ReservationsEntity res where res.cart.user.userId = :userId")
    List<ReservationsEntity> findAllByUserId(Integer userId);

    @Query("select res from ReservationsEntity res where res.reservationNumber = :reservationNumber")
    Optional<ReservationsEntity> findByReservationNumber(String reservationNumber);


    @Modifying
    @Query("delete from ReservationsEntity res where res.cart.user.userId = :userId")
    void deleteByCartUserId(Integer userId);
}
