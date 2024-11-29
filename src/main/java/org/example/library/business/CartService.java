package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.api.dto.BooksDTO;
import org.example.library.api.dto.CartDTO;
import org.example.library.api.dto.CartItemDTO;
import org.example.library.business.dao.CartDao;
import org.example.library.domain.Cart;
import org.example.library.domain.exception.NotEnoughCopiesException;
import org.example.library.domain.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {

    private final CartDao cartDao;

    @Transactional
    public void saveCart(CartDTO cartDTO, Integer userId) {
        if (cartDao.findByUserId(userId).isPresent()) {
            cartDao.findByUserId(userId).get();
        }
        Cart cart = registerCart(cartDTO, userId);
        cartDao.saveCart(cart);
    }

    private Cart registerCart(CartDTO cartDTO, Integer userId) {
        return Cart.builder()
                .userId(userId).build();
    }

    public Cart findCartByUserId(Integer userId) {
        Optional<Cart> cart= cartDao.findByUserId(userId);
        if (cart.isEmpty()) {
            throw new NotFoundException("Could not find user with userId: " + userId);
        }
        return cart.get();
    }

}
