package org.example.library.infrastructure.database.repository.jpa;

import org.example.library.infrastructure.database.entity.ReservationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationsJpaRepository extends JpaRepository<ReservationsEntity, Integer> {

    @Query("select res from ReservationsEntity res where res.cart.user.userId = :userId")
    Optional<ReservationsEntity> findByUserId(Integer userId);
}
