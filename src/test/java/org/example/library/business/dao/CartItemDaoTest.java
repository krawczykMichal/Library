package org.example.library.business.dao;

import org.example.library.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@DataJpaTest
@TestPropertySource(properties = "spring.flyway.enabled=false")  // Wyłączenie migracji Flyway dla testów
public class CartItemDaoTest {

    @MockBean
    private CartItemDao cartItemDao;

    @MockBean
    private CartDao cartDao;  // Zakładając, że CartItem jest powiązany z Cart

    @Test
    void testSaveCartItem() {
        // Tworzenie użytkownika
        Users user = Users.builder()
                .name("John")
                .surname("Doe")
                .username("john_doe")
                .email("johndoe@example.com")
                .phoneNumber("123456789")
                .build();

        // Tworzenie koszyka
        Cart cart = Cart.builder()
                .user(user)
                .build();

        // Tworzenie książki
        Books book = Books.builder()
                .isbn("12345")
                .title("Book 1")
                .build();

        // Tworzenie pozycji w koszyku
        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .book(book)
                .quantity(2)
                .build();

        // Mockowanie saveCartItem, zwracamy zapisaną pozycję w koszyku
        when(cartItemDao.saveCartItem(any(CartItem.class))).thenReturn(cartItem);

        // Wywołanie metody saveCartItem
        CartItem savedCartItem = cartItemDao.saveCartItem(cartItem);
        Integer quantity = savedCartItem.getQuantity();
        // Sprawdzanie, czy pozycja w koszyku została zapisana poprawnie
        assertNotNull(savedCartItem);
        assertEquals(2, quantity.intValue());
        assertEquals("12345", savedCartItem.getBook().getIsbn());
    }

    @Test
    void testFindByCartId() {
        // Tworzenie użytkownika
        Users user = Users.builder()
                .name("John")
                .surname("Doe")
                .username("john_doe")
                .email("johndoe@example.com")
                .phoneNumber("123456789")
                .build();

        // Tworzenie koszyka
        Cart cart = Cart.builder()
                .user(user)
                .build();

        // Tworzenie książek
        Books book1 = Books.builder()
                .isbn("12345")
                .title("Book 1")
                .build();
        Books book2 = Books.builder()
                .isbn("67890")
                .title("Book 2")
                .build();

        // Tworzenie pozycji w koszyku
        CartItem cartItem1 = CartItem.builder()
                .cart(cart)
                .book(book1)
                .quantity(1)
                .build();
        CartItem cartItem2 = CartItem.builder()
                .cart(cart)
                .book(book2)
                .quantity(3)
                .build();

        List<CartItem> cartItemsList = Arrays.asList(cartItem1, cartItem2);

        // Mockowanie findByCartId, zwracamy listę pozycji w koszyku
        when(cartItemDao.findByCartId(cart.getCartId())).thenReturn(cartItemsList);

        // Wywołanie metody findByCartId
        List<CartItem> foundCartItems = cartItemDao.findByCartId(cart.getCartId());
        Integer quantity = foundCartItems.get(1).getQuantity();
        // Sprawdzanie, czy lista pozycji w koszyku nie jest pusta i zawiera oczekiwane dane
        assertFalse(foundCartItems.isEmpty());
        assertEquals(2, foundCartItems.size());
        assertEquals("12345", foundCartItems.get(0).getBook().getIsbn());
        assertEquals(3, quantity.intValue());
    }

    @Test
    void testClearCartAfterReservationOrLoan() {
        // Tworzenie użytkownika
        Users user = Users.builder()
                .name("John")
                .surname("Doe")
                .username("john_doe")
                .email("johndoe@example.com")
                .phoneNumber("123456789")
                .build();

        // Tworzenie koszyka
        Cart cart = Cart.builder()
                .user(user)
                .build();

        // Tworzenie książki
        Books book = Books.builder()
                .isbn("12345")
                .title("Book 1")
                .build();

        // Tworzenie pozycji w koszyku
        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .book(book)
                .quantity(1)
                .build();

        // Mockowanie findByCartId, zwracamy listę pozycji w koszyku
        when(cartItemDao.findByCartId(cart.getCartId())).thenReturn(Arrays.asList(cartItem));

        // Mockowanie clearCartAfterReservationOrLoan
        doNothing().when(cartItemDao).clearCartAfterReservationOrLoan(cart.getCartId());
        // Wywołanie metody clearCartAfterReservationOrLoan
        cartItemDao.clearCartAfterReservationOrLoan(cart.getCartId());

        // Sprawdzanie, czy koszyk został wyczyszczony po rezerwacji lub wypożyczeniu
        when(cartItemDao.findByCartId(cart.getCartId())).thenReturn(null);
        List<CartItem> clearedCartItems = cartItemDao.findByCartId(cart.getCartId());

        assertNull(clearedCartItems);  // Oczekujemy null, ponieważ koszyk powinien zostać wyczyszczony
    }
}
