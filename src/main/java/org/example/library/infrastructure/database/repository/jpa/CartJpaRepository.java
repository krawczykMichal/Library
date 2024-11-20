package org.example.library.infrastructure.database.repository.jpa;

import org.example.library.domain.Cart;
import org.example.library.infrastructure.database.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartJpaRepository extends JpaRepository<CartEntity, Integer> {

    @Query("select cart from CartEntity cart where cart.user.userId = :userId")
    Optional<Cart> findByUserId(Integer userId);
}
