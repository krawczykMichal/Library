package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.ReservationItemDao;
import org.example.library.domain.ReservationItem;
import org.example.library.infrastructure.database.entity.ReservationItemEntity;
import org.example.library.infrastructure.database.repository.jpa.ReservationItemJpaRepository;
import org.example.library.infrastructure.database.repository.mapper.ReservationItemEntityMapper;
import org.example.library.infrastructure.database.repository.mapper.ReservationItemEntityMapperClass;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ReservationItemRepository implements ReservationItemDao {

    private final ReservationItemJpaRepository reservationItemJpaRepository;
    private final ReservationItemEntityMapper reservationItemEntityMapper;
    private final ReservationItemEntityMapperClass reservationItemEntityMapperClass;

    @Override
    public void deleteByReservationCartUserId(Integer userId) {
        reservationItemJpaRepository.deleteByReservationCartUserId(userId);
    }

    @Override
    public void deleteByReservationNumber(String reservationNumber) {
        reservationItemJpaRepository.deleteByReservationNumber(reservationNumber);
    }

    @Override
    public void saveAll(List<ReservationItem> reservationItemList) {
        List<ReservationItemEntity> entities = reservationItemEntityMapper.mapToReservationItemEntityList(reservationItemList);
        reservationItemJpaRepository.saveAll(entities);
    }

    @Override
    public ReservationItem save(ReservationItem reservationItem) {
        ReservationItemEntity toSave = reservationItemEntityMapperClass.mapToReservationItemEntity(reservationItem);
        ReservationItemEntity saved = reservationItemJpaRepository.save(toSave);
        return reservationItemEntityMapperClass.mapFromReservationItemEntity(saved);
    }
}
