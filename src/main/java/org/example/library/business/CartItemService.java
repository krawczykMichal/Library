package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.CartItemDao;
import org.example.library.domain.Books;
import org.example.library.domain.Cart;
import org.example.library.domain.CartItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CartItemService {

    private final CartItemDao cartItemDao;

    @Transactional
    public void addToCartItem(Books book, Cart cart) {
        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .book(book)
                .title(book.getTitle())
                .quantity(4).build();
        cartItemDao.saveCartItem(cartItem);
    }

    public List<CartItem> findByCartId(Integer cartId) {
        return cartItemDao.findByCartId(cartId);
    }
}
