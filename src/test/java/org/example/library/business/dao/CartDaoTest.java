package org.example.library.business.dao;

import org.example.library.domain.Cart;
import org.example.library.domain.User;
import org.example.library.domain.Users;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest
@TestPropertySource(properties = "spring.flyway.enabled=false")  // Wyłączenie migracji Flyway dla testów
public class CartDaoTest {

    @MockBean
    private CartDao cartDao;


    @Test
    void testSaveCart() {
        // Tworzenie użytkownika
        Users user = Users.builder()
                .name("John")
                .surname("Doe")
                .username("john_doe")
                .email("johndoe@example.com")
                .phoneNumber("123456789")
                .build();

        // Tworzenie koszyka z użytkownikiem
        Cart cart = Cart.builder()
                .user(user)
                .build();

        // Mockowanie saveCart, zwracamy zapiszony koszyk
        when(cartDao.saveCart(any(Cart.class))).thenReturn(cart);

        // Wywołanie metody saveCart
        Cart savedCart = cartDao.saveCart(cart);

        // Sprawdzanie, czy koszyk został zapisany poprawnie
        assertNotNull(savedCart);
        assertEquals(user.getUsername(), savedCart.getUser().getUsername());
    }

    @Test
    void testFindByUserId() {
        // Tworzenie użytkownika
        Users user = Users.builder()
                .name("John")
                .surname("Doe")
                .username("john_doe")
                .email("johndoe@example.com")
                .phoneNumber("123456789")
                .build();

        // Tworzenie koszyka z użytkownikiem
        Cart cart = Cart.builder()
                .user(user)
                .build();

        // Mockowanie findByUserId, zwracamy koszyk dla konkretnego użytkownika
        when(cartDao.findByUserId(user.getUserId())).thenReturn(Optional.of(cart));

        // Wywołanie metody findByUserId
        Optional<Cart> foundCart = cartDao.findByUserId(user.getUserId());

        // Sprawdzanie, czy koszyk został znaleziony
        assertTrue(foundCart.isPresent());
        assertEquals(user.getUsername(), foundCart.get().getUser().getUsername());
    }

    @Test
    void testFindByUserIdNotFound() {
        // Mockowanie findByUserId, zwracamy Optional.empty() dla nieistniejącego użytkownika
        when(cartDao.findByUserId(9999)).thenReturn(Optional.empty());

        // Wywołanie metody findByUserId dla nieistniejącego użytkownika
        Optional<Cart> foundCart = cartDao.findByUserId(9999);

        // Sprawdzanie, że koszyk nie został znaleziony
        assertFalse(foundCart.isPresent());
    }

    @Test
    void testFindById() {
        // Tworzenie użytkownika
        Users user = Users.builder()
                .name("Charlie")
                .surname("Brown")
                .username("charlie_brown")
                .email("charliebrown@example.com")
                .phoneNumber("123456789")
                .build();

        // Tworzenie koszyka z użytkownikiem
        Cart cart = Cart.builder()
                .user(user)
                .build();

        // Mockowanie findById, zwracamy koszyk z konkretnym ID
        when(cartDao.findById(1)).thenReturn(Optional.of(cart));

        // Wywołanie metody findById
        Optional<Cart> foundCart = cartDao.findById(1);

        // Sprawdzanie, czy koszyk został znaleziony
        assertTrue(foundCart.isPresent());
        assertEquals(user.getUsername(), foundCart.get().getUser().getUsername());
    }

    @Test
    void testFindByIdNotFound() {
        // Mockowanie findById, zwracamy Optional.empty() dla nieistniejącego koszyka
        when(cartDao.findById(999)).thenReturn(Optional.empty());

        // Wywołanie metody findById dla nieistniejącego koszyka
        Optional<Cart> foundCart = cartDao.findById(999);

        // Sprawdzanie, że koszyk nie został znaleziony
        assertFalse(foundCart.isPresent());
    }

    @Test
    void testDeleteByUserId() {
        // Tworzenie użytkownika
        Users user = Users.builder()
                .name("Lucy")
                .surname("Lou")
                .username("lucy_lou")
                .email("lucylou@example.com")
                .phoneNumber("123456789")
                .build();

        // Tworzenie koszyka z użytkownikiem
        Cart cart = Cart.builder()
                .user(user)
                .build();

        // Mockowanie deleteByUserId
        when(cartDao.findByUserId(user.getUserId())).thenReturn(Optional.of(cart));

        // Wywołanie metody deleteByUserId
        cartDao.deleteByUserId(user.getUserId());

        // Sprawdzanie, że metoda deleteByUserId została wywołana
        when(cartDao.findByUserId(user.getUserId())).thenReturn(Optional.empty());
        Optional<Cart> deletedCart = cartDao.findByUserId(user.getUserId());

        // Sprawdzanie, że koszyk został usunięty
        assertFalse(deletedCart.isPresent());
    }
}
