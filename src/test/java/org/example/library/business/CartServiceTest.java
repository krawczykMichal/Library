package org.example.library.business;
import org.example.library.api.dto.CartItemDTO;
import org.example.library.business.dao.CartDao;
import org.example.library.business.dao.CartItemDao;
import org.example.library.domain.Books;
import org.example.library.domain.Cart;
import org.example.library.domain.Users;
import org.example.library.domain.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartDao cartDao;

    @Mock
    private CartItemService cartItemService;

    @Mock
    private CartItemDao cartItemDao;

    @InjectMocks
    private CartService cartService;

    private Users user;
    private Cart cart;
    private Books book;
    private CartItemDTO cartItemDTO;

    @BeforeEach
    void setUp() {
        user = Users.builder()
                .userId(1)
                .username("testUser")
                .build();

        cart = Cart.builder()
                .cartId(1)
                .user(user)
                .build();

        book = Books.builder()
                .title("Test Book")
                .isbn("1234567890")
                .publisher("Test Publisher")
                .copies(5)
                .available(true)
                .build();

        cartItemDTO = CartItemDTO.builder()
                .quantity(2)
                .build();
    }

    @Test
    void shouldSaveCartForUser() {
        when(cartDao.findByUserId(user.getUserId())).thenReturn(Optional.empty());

        Cart savedCart = cartService.saveCart(user);

        assertNotNull(savedCart);
        assertEquals(user, savedCart.getUser());
        verify(cartDao, times(1)).saveCart(any(Cart.class));
    }

    @Test
    void shouldFindCartByUserId() {
        when(cartDao.findByUserId(1)).thenReturn(Optional.of(cart));

        Cart foundCart = cartService.findCartByUserId(1);

        assertNotNull(foundCart);
        assertEquals(1, foundCart.getCartId());
        verify(cartDao, times(1)).findByUserId(1);
    }

    @Test
    void shouldThrowExceptionWhenCartNotFoundByUserId() {
        when(cartDao.findByUserId(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> cartService.findCartByUserId(1));
    }

    @Test
    void shouldFindCartById() {
        when(cartDao.findById(1)).thenReturn(Optional.of(cart));

        Cart foundCart = cartService.findById(1);

        assertNotNull(foundCart);
        assertEquals(1, foundCart.getCartId());
        verify(cartDao, times(1)).findById(1);
    }

    @Test
    void shouldThrowExceptionWhenCartNotFoundById() {
        when(cartDao.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> cartService.findById(1));
    }

    @Test
    void shouldAddItemToCart() {
        cartService.addItemToCart(cart, book, cartItemDTO);
        verify(cartItemService, times(1)).addToCartItem(book, cart, cartItemDTO);
    }

    @Test
    void shouldClearCart() {
        cartService.clearCart(1);
        verify(cartItemDao, times(1)).clearCartAfterReservationOrLoan(1);
    }
}