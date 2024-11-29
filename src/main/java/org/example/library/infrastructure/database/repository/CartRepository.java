package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.api.dto.BooksDTO;
import org.example.library.api.dto.CartItemDTO;
import org.example.library.business.dao.CartDao;
import org.example.library.domain.Cart;
import org.example.library.infrastructure.database.entity.CartEntity;
import org.example.library.infrastructure.database.repository.jpa.CartJpaRepository;
import org.example.library.infrastructure.database.repository.mapper.CartEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class CartRepository implements CartDao {

    private final CartJpaRepository cartJpaRepository;

    private final CartEntityMapper cartEntityMapper;

    @Override
    public Cart saveCart(Cart cart1) {
        CartEntity toSave = cartEntityMapper.mapToCartEntity(cart1);
        CartEntity saved = cartJpaRepository.save(toSave);
        return cartEntityMapper.mapFromCartEntity(saved);
    }

    @Override
    public Optional<Cart> findByUserId(Integer userId) {
        return cartJpaRepository.findByUserId(userId);
    }

}
