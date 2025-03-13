package org.example.library.infrastructure.database.repository.jpa;

import org.example.library.infrastructure.database.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemJpaRepository extends JpaRepository<CartItemEntity, Integer> {

    @Query("select ci from CartItemEntity ci where ci.cart.cartId = :cartId")
    List<CartItemEntity> findByCartId(Integer cartId);

    @Modifying
    @Query("delete from CartItemEntity ci where ci.cart.cartId = :cartId")
    void clearCartAfterReservationOrLoan(Integer cartId);
}
