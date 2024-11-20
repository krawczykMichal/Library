package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.CartItemDao;
import org.example.library.domain.CartItem;
import org.example.library.infrastructure.database.entity.CartItemEntity;
import org.example.library.infrastructure.database.repository.jpa.CartItemJpaRepository;
import org.example.library.infrastructure.database.repository.mapper.CartItemEntityMapper;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CartItemRepository implements CartItemDao {

    private final CartItemEntityMapper cartItemEntityMapper;
    private final CartItemJpaRepository cartItemJpaRepository;

    @Override
    public CartItem saveCartItem(CartItem cartItem) {
        CartItemEntity toSave = cartItemEntityMapper.mapToCartItemEntity(cartItem);
        CartItemEntity saved = cartItemJpaRepository.save(toSave);
        return cartItemEntityMapper.mapFromCartItemEntity(saved);
    }
}
