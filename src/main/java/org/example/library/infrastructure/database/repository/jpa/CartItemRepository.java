package org.example.library.infrastructure.database.repository.jpa;

import org.example.library.infrastructure.database.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Integer> {
}
