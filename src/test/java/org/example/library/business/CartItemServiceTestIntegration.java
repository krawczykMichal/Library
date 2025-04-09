package org.example.library.business;

import jakarta.transaction.Transactional;
import org.example.library.api.dto.CartItemDTO;
import org.example.library.business.dao.*;
import org.example.library.domain.*;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class CartItemServiceTestIntegration {


    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartItemDao cartItemDao;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private BooksDao booksDao;

    @Autowired
    private AuthorsDao authorsDao;

    @Autowired
    private CategoriesDao categoriesDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private Flyway flyway;

    private Books sampleBook;
    private Cart sampleCart;
    private Authors sampleAuthor;
    private Categories sampleCategories;
    private Users sampleUsers;
    private CartItemDTO cartItemDTO;

    @BeforeEach
    void setUp() {
        flyway.clean();
        flyway.migrate();

        sampleAuthor = Authors.builder()
                .name("jan")
                .surname("nowak")
                .authorCode("12308712")
                .build();
        Authors authors = authorsDao.saveAuthor(sampleAuthor);

        sampleCategories = Categories.builder()
                .name("Nowa")
                .build();
        Categories categories = categoriesDao.saveCategory(sampleCategories);

        sampleBook = Books.builder()
                .bookId(151)
                .title("Dune")
                .isbn("123-456")
                .publisher("SciFi Press")
                .publishedDate(2020)
                .copies(10)
                .available(true)
                .author(authors)
                .category(categories)
                .build();
        booksDao.saveBook(sampleBook);

        sampleUsers = Users.builder()
                .name("Janusz")
                .surname("Kowalski")
                .email("Jkowalskii@email.com")
                .username("jKowalskii")
                .phoneNumber("123456789")
                .build();
        Users users = usersDao.saveUser(sampleUsers);

        sampleCart = Cart.builder()
                .cartId(1)
                .user(users)
                .cartItem(new ArrayList<>())
                .build();
        cartDao.saveCart(sampleCart);

        cartItemDTO = CartItemDTO.builder().quantity(2).build();
    }

    @Transactional
    @Test
    void testAddToCartItem() {
        // When
        cartItemService.addToCartItem(sampleBook, sampleCart, cartItemDTO);

        // Then
        List<CartItem> cartItems = cartItemDao.findByCartId(sampleCart.getCartId());
        assertEquals(1, cartItems.size());

        CartItem savedCartItem = cartItems.get(0);
        assertEquals("Dune", savedCartItem.getTitle());
        assertEquals(2, savedCartItem.getQuantity());
        assertEquals(sampleBook, savedCartItem.getBook());
        assertEquals(sampleCart, savedCartItem.getCart());
    }

    @Transactional
    @Test
    void testClearCartAfterReservationOrLoan() {
        // Given
        cartItemService.addToCartItem(sampleBook, sampleCart, cartItemDTO);
        assertFalse(cartItemDao.findByCartId(sampleCart.getCartId()).isEmpty());

        // When
        cartItemService.clearCartAfterReservationOrLoan(sampleCart.getCartId());

        // Then
        assertTrue(cartItemDao.findByCartId(sampleCart.getCartId()).isEmpty());
    }
}
