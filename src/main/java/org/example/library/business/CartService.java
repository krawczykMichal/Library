package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.api.dto.BooksDTO;
import org.example.library.api.dto.CartDTO;
import org.example.library.api.dto.CartItemDTO;
import org.example.library.business.dao.CartDao;
import org.example.library.domain.Books;
import org.example.library.domain.Cart;
import org.example.library.domain.Users;
import org.example.library.domain.exception.NotEnoughCopiesException;
import org.example.library.domain.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {

    private final CartDao cartDao;
    private final CartItemService cartItemService;

    @Transactional
    public Cart saveCart(Users user) {
        if (cartDao.findByUserId(user.getUserId()).isPresent()) {
            cartDao.findByUserId(user.getUserId()).get();
        }
        Cart cart = registerCart(user);
        cartDao.saveCart(cart);

        return cart;
    }

    private Cart registerCart(Users user) {
        return Cart.builder()
                .user(user).build();
    }

    public Cart findCartByUserId(Integer userId) {
        Optional<Cart> cart= cartDao.findByUserId(userId);
        if (cart.isEmpty()) {
            throw new NotFoundException("Could not find user with userId: " + userId);
        }
        return cart.get();
    }


    public Cart findById(Integer cartId) {
        Optional<Cart> cart = cartDao.findById(cartId);
        if (cart.isEmpty()) {
            throw new NotFoundException("Could not find cart with cartId: " + cartId);
        }
        return cart.get();
    }

    @Transactional
    public void addItemToCart(Cart cart, Books book) {
        cartItemService.addToCartItem(book, cart);
    }
}
