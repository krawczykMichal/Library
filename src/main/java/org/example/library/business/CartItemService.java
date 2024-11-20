package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.CartItemDao;
import org.example.library.domain.Books;
import org.example.library.domain.CartItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CartItemService {

    private final CartItemDao cartItemDao;

    @Transactional
    public void addToCartItem(Books book) {
        CartItem cartItem = CartItem.builder()
                .book(book)
                .quantity(1).build();
        cartItemDao.saveCartItem(cartItem);
    }
}
