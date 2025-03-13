package org.example.library.infrastructure.database.repository.jpa;

import io.micrometer.common.KeyValues;
import org.example.library.infrastructure.database.entity.ReservationsHistoryEntity;
import org.example.library.infrastructure.security.repository.mapper.UserRoleEntityMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationsHistoryJpaRepository extends JpaRepository<ReservationsHistoryEntity, Integer> {

    @Query("select rh from ReservationsHistoryEntity rh where rh.users.userId = :userId")
    List<ReservationsHistoryEntity> findAllByUserId(Integer userId);

    @Query("select rh from ReservationsHistoryEntity rh where rh.reservationNumber = :reservationNumber")
    Optional<ReservationsHistoryEntity> findByReservationNumber(String reservationNumber);
}
