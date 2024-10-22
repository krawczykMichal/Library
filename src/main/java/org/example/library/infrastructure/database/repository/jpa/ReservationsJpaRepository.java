package org.example.library.infrastructure.database.repository.jpa;

import org.example.library.infrastructure.database.entity.ReservationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationsJpaRepository extends JpaRepository<ReservationsEntity, Integer> {
}
