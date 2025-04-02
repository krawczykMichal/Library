package org.example.library.business;
import org.example.library.api.dto.CartItemDTO;
import org.example.library.business.dao.BooksDao;
import org.example.library.business.dao.CartItemDao;
import org.example.library.domain.Books;
import org.example.library.domain.Cart;
import org.example.library.domain.CartItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartItemServiceTest {

    @Mock
    private CartItemDao cartItemDao;

    @Mock
    private BooksDao booksDao;

    @InjectMocks
    private CartItemService cartItemService;

    private Books book;
    private Cart cart;
    private CartItemDTO cartItemDTO;

    @BeforeEach
    void setUp() {
        book = Books.builder()
                .title("Test Book")
                .isbn("1234567890")
                .publisher("Test Publisher")
                .copies(5)
                .available(true)
                .build();

        cart = Cart.builder()
                .cartId(1)
                .build();

        cartItemDTO = CartItemDTO.builder()
                .quantity(2)
                .build();
    }

    @Test
    void shouldAddToCartItem() {
        when(booksDao.findByIsbn("1234567890")).thenReturn(Optional.of(book));

        cartItemService.addToCartItem(book, cart, cartItemDTO);

        verify(cartItemDao, times(1)).saveCartItem(any(CartItem.class));
    }

    @Test
    void shouldClearCartAfterReservationOrLoan() {
        cartItemService.clearCartAfterReservationOrLoan(1);
        verify(cartItemDao, times(1)).clearCartAfterReservationOrLoan(1);
    }
}
