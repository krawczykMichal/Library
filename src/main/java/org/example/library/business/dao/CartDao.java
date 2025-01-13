package org.example.library.business.dao;

import org.example.library.api.dto.BooksDTO;
import org.example.library.api.dto.CartItemDTO;
import org.example.library.domain.Cart;

import java.util.Optional;

public interface CartDao {

    Cart saveCart(Cart cart1);

    Optional<Cart> findByUserId(Integer userId);

    Optional<Cart> findById(Integer cartId);
}
