package org.example.library.infrastructure.database.repository.mapper;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.example.library.business.dao.BooksDao;
import org.example.library.business.dao.CartDao;
import org.example.library.domain.*;
import org.example.library.infrastructure.database.entity.*;
import org.example.library.infrastructure.database.repository.jpa.BooksJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CartItemEntityMapperClass {

    private final BooksDao booksDao;
    private final BooksEntityMapper booksEntityMapper;
    private final CartDao cartDao;
    private final CartEntityMapper cartEntityMapper;
    private final BooksJpaRepository booksJpaRepository;


    public CartItem mapFromCartItemEntity(CartItemEntity cartItemEntity) {
        if (cartItemEntity == null) {
            return null;
        }

        return CartItem.builder()
                .cartItemId(cartItemEntity.getCartItemId())
                .title(cartItemEntity.getTitle())
                .quantity(cartItemEntity.getQuantity())
                .cart(mapFromCartEntity(cartItemEntity.getCart()))
                .book(mapFromBooksEntity(cartItemEntity.getBook()))
                .build();
    }

    private Cart mapFromCartEntity(CartEntity cartEntity) {
        if (cartEntity == null) {
            return null;
        }

        return Cart.builder()
                .cartId(cartEntity.getCartId())
                .user(mapFromUsersEntity(cartEntity.getUser()))
                .build();
    }

    private Users mapFromUsersEntity(UsersEntity usersEntity) {
        if (usersEntity == null) {
            return null;
        }

        return Users.builder()
                .userId(usersEntity.getUserId())
                .name(usersEntity.getName())
                .surname(usersEntity.getSurname())
                .username(usersEntity.getUsername())
                .email(usersEntity.getEmail())
                .phoneNumber(usersEntity.getPhoneNumber())
                .build();
    }

    private Books mapFromBooksEntity(BooksEntity booksEntity) {
        if (booksEntity == null) {
            return null;
        }

        return Books.builder()
                .bookId(booksEntity.getBookId())
                .publishedDate(booksEntity.getPublishedDate())
                .title(booksEntity.getTitle())
                .category(mapFromCategoriesEntity(booksEntity.getCategory()))
                .author(mapFromAuthorsEntity(booksEntity.getAuthor()))
                .isbn(booksEntity.getIsbn())
                .available(booksEntity.getAvailable())
                .build();
    }

    private Categories mapFromCategoriesEntity(CategoriesEntity categoriesEntity) {
        if (categoriesEntity == null) {
            return null;
        }

        return Categories.builder()
                .categoryId(categoriesEntity.getCategoryId())
                .name(categoriesEntity.getName()).build();
    }

    private Authors mapFromAuthorsEntity(AuthorsEntity authorsEntity) {
        if (authorsEntity == null) {
            return null;
        }

        return Authors.builder()
                .authorId(authorsEntity.getAuthorId())
                .authorCode(authorsEntity.getAuthorCode())
                .name(authorsEntity.getName())
                .surname(authorsEntity.getSurname()).build();
    }

    public CartItemEntity mapToCartItemEntity(CartItem cartItem) {
        if (cartItem == null) {
            return null;
        }

        return CartItemEntity.builder()
                .cartItemId(cartItem.getCartItemId())
                .title(cartItem.getTitle())
                .quantity(cartItem.getQuantity())
                .book(findBook(cartItem.getBook().getIsbn()))
                .cart(mapToCartEntity(cartItem.getCart().getCartId()))
                .build();
    }

    private BooksEntity findBook(String isbn) {
        Optional<Books> byIsbn = booksDao.findByIsbn(isbn);
        Books books = byIsbn.get();
        System.out.println("Books: " + books);

        Optional<BooksEntity> booksEntityOptional = booksJpaRepository.findByIsbn(isbn);
        BooksEntity booksEntity = booksEntityOptional.get();



        return booksEntity;

    }


    private CartEntity mapToCartEntity(Integer cartId) {
        Optional<Cart> byId = cartDao.findById(cartId);
        Cart cart = byId.get();
        System.out.println("Cart: " + cart);

        return cartEntityMapper.mapToCartEntity(cart);
    }

    private UsersEntity mapToUsersEntity(Users users) {
        if (users == null) {
            return null;
        }

        return UsersEntity.builder()
                .userId(users.getUserId())
                .name(users.getName())
                .surname(users.getSurname())
                .username(users.getUsername())
                .email(users.getEmail())
                .phoneNumber(users.getPhoneNumber())
                .build();
    }
}
