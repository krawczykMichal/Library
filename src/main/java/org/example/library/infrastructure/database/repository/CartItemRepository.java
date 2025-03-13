package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.CartItemDao;
import org.example.library.domain.CartItem;
import org.example.library.infrastructure.database.entity.CartItemEntity;
import org.example.library.infrastructure.database.repository.jpa.CartItemJpaRepository;
import org.example.library.infrastructure.database.repository.mapper.CartItemEntityMapper;
import org.example.library.infrastructure.database.repository.mapper.CartItemEntityMapperClass;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CartItemRepository implements CartItemDao {

    private final CartItemEntityMapper cartItemEntityMapper;
    private final CartItemJpaRepository cartItemJpaRepository;
    private final CartItemEntityMapperClass cartItemEntityMapperClass;

    @Override
    public CartItem saveCartItem(CartItem cartItem) {
        CartItemEntity toSave = cartItemEntityMapperClass.mapToCartItemEntity(cartItem);
        CartItemEntity saved = cartItemJpaRepository.save(toSave);
        return cartItemEntityMapperClass.mapFromCartItemEntity(saved);
    }

    @Override
    public List<CartItem> findByCartId(Integer cartId) {
        return cartItemJpaRepository.findByCartId(cartId).stream().map(cartItemEntityMapperClass::mapFromCartItemEntity).toList();
    }

    @Override
    public void clearCartAfterReservationOrLoan(Integer cartId) {
        cartItemJpaRepository.clearCartAfterReservationOrLoan(cartId);
    }
}
