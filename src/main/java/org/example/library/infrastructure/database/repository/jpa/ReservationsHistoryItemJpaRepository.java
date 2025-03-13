package org.example.library.infrastructure.database.repository.jpa;

import org.example.library.infrastructure.database.entity.ReservationsHistoryEntity;
import org.example.library.infrastructure.database.entity.ReservationsHistoryItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationsHistoryItemJpaRepository extends JpaRepository<ReservationsHistoryItemEntity, Integer> {
}
