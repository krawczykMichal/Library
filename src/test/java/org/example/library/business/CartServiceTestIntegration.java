package org.example.library.business;

import org.example.library.business.dao.CartDao;
import org.example.library.business.dao.UsersDao;
import org.example.library.domain.Cart;
import org.example.library.domain.Users;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class CartServiceTestIntegration {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private Flyway flyway;

    private Users sampleUser;

    @BeforeEach
    void setUp() {
        flyway.clean();
        flyway.migrate();

        sampleUser = Users.builder()
                .name("John")
                .surname("Doe")
                .email("john.doe@example.com")
                .phoneNumber("123456789")
                .username("johndoe")
                .build();

    }

    @Test
    void testSaveCart() {
        // When
        Users users = usersDao.saveUser(sampleUser);
        Cart savedCart = cartService.saveCart(users);

        // Then
        assertNotNull(savedCart);
        assertEquals(users, savedCart.getUser());
    }

    @Test
    @Transactional
    void testFindCartByUserId() {
        // Given
        Users users = usersDao.saveUser(sampleUser);
        cartService.saveCart(users);
        Cart cartByUserId = cartService.findCartByUserId(users.getUserId());

        // When
        Cart foundCart = cartService.findCartByUserId(users.getUserId());

        // Then
        assertNotNull(foundCart);
        assertEquals(cartByUserId.getCartId(), foundCart.getCartId());
    }

    @Test
    @Transactional
    void testFindById() {
        // Given
        Users users = usersDao.saveUser(sampleUser);
        cartService.saveCart(users);
        Cart cartByUserId = cartService.findCartByUserId(users.getUserId());

        // When
        Cart foundCart = cartService.findById(cartByUserId.getCartId());

        // Then
        assertNotNull(foundCart);
        assertEquals(cartByUserId.getCartId(), foundCart.getCartId());
    }

    @Test
    @Transactional
    void testClearCart() {
        // Given
        Users users = usersDao.saveUser(sampleUser);
        cartService.saveCart(users);
        Cart cartByUserId = cartService.findCartByUserId(users.getUserId());

        // When
        cartService.clearCart(cartByUserId.getCartId());

        // Then
        Cart emptyCart = cartService.findById(cartByUserId.getCartId());
        assertNull(emptyCart.getCartItem());
    }
}