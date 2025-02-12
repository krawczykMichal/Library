package org.example.library.business.dao;

import org.example.library.domain.CartItem;

import java.util.List;

public interface CartItemDao {

    CartItem saveCartItem(CartItem cartItem);

    List<CartItem> findByCartId(Integer cartId);
}
