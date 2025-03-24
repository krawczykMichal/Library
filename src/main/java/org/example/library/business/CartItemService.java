package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.api.dto.CartItemDTO;
import org.example.library.business.dao.BooksDao;
import org.example.library.business.dao.CartItemDao;
import org.example.library.domain.Books;
import org.example.library.domain.Cart;
import org.example.library.domain.CartItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartItemService {

    private final CartItemDao cartItemDao;
    private final BooksDao booksDao;

    @Transactional
    public void addToCartItem(Books book, Cart cart, CartItemDTO cartItemDTO) {
        Optional<Books> byIsbn = booksDao.findByIsbn(book.getIsbn());
        Books books = byIsbn.get();

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .title(book.getTitle())
                .quantity(cartItemDTO.getQuantity()).build();

        CartItem cartItem1 = cartItem.withBook(book);

        cartItemDao.saveCartItem(cartItem1);
        System.out.println("cartItem: " + cartItem1);
    }

    public List<CartItem> findByCartId(Integer cartId) {
        return cartItemDao.findByCartId(cartId);
    }

    @Transactional
    public void clearCartAfterReservationOrLoan(Integer cartId) {
        cartItemDao.clearCartAfterReservationOrLoan(cartId);
    }
}
