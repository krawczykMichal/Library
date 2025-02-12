package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.*;
import org.example.library.infrastructure.database.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartEntityMapperClass {

    public Cart mapFromCartEntity(CartEntity cartEntity) {
        if (cartEntity == null) {
            return null;
        }

        return Cart.builder()
                .cartId(cartEntity.getCartId())
                .cartItem(mapFromCartItemEntity(cartEntity.getCartItems()))
                .user(mapFromUsersEntity(cartEntity.getUser()))
                .build();
    }

    private List<CartItem> mapFromCartItemEntity(List<CartItemEntity> cartItemEntity) {
        if (cartItemEntity == null) {
            return null;
        }

        return cartItemEntity.stream()
                .map(entities -> CartItem.builder()
                        .cartItemId(entities.getCartItemId())
                        .title(entities.getTitle())
                        .book(mapFromBooksEntity(entities.getBooks()))
                        .quantity(entities.getQuantity())
                        .build()
                )
                .toList();
    }

    private Books mapFromBooksEntity(BooksEntity booksEntity) {
        if (booksEntity == null) {
            return null;
        }

        return Books.builder()
                .bookId(booksEntity.getBookId())
                .title(booksEntity.getTitle())
                .category(mapFromCategoriesEntity(booksEntity.getCategory()))
                .author(mapFromAuthorsEntity(booksEntity.getAuthor()))
                .isbn(booksEntity.getIsbn())
                .available(booksEntity.getAvailable())
                .publisher(booksEntity.getPublisher())
                .publishedDate(booksEntity.getPublishedDate())
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

    private Users mapFromUsersEntity(UsersEntity usersEntity) {
        if (usersEntity == null) {
            return null;
        }

        return Users.builder()
                .userId(usersEntity.getUserId())
                .name(usersEntity.getName())
                .surname(usersEntity.getSurname())
                .username(usersEntity.getUsername())
                .phoneNumber(usersEntity.getPhoneNumber())
                .email(usersEntity.getEmail())
                .build();
    }


    public CartEntity mapToCartEntity(Cart cart) {
        if (cart == null) {
            return null;
        }

        return CartEntity.builder()
                .cartId(cart.getCartId())
                .cartItems(mapToCartItemEntity(cart.getCartItem()))
                .user(mapToUsersEntity(cart.getUser()))
                .build();
    }

    private List<CartItemEntity> mapToCartItemEntity(List<CartItem> cartItem) {
        if (cartItem == null) {
            return null;
        }

        return cartItem.stream().map(entities -> CartItemEntity.builder()
                .cartItemId(entities.getCartItemId())
                .books(mapToBooksEntity(entities.getBook()))
                .title(entities.getTitle())
                .quantity(entities.getQuantity())
                .build()).toList();
    }

    private BooksEntity mapToBooksEntity(Books books) {
        if (books == null) {
            return null;
        }

        return BooksEntity.builder()
                .bookId(books.getBookId())
                .title(books.getTitle())
                .category(mapToCategoriesEntity(books.getCategory()))
                .isbn(books.getIsbn())
                .publishedDate(books.getPublishedDate())
                .publisher(books.getPublisher())
                .author(mapToAuthorsEntity(books.getAuthor()))
                .copies(books.getCopies())
                .available(books.getAvailable())
                .build();
    }

    private AuthorsEntity mapToAuthorsEntity(Authors authors) {
        if (authors == null) {
            return null;
        }

        return AuthorsEntity.builder()
                .authorId(authors.getAuthorId())
                .authorCode(authors.getAuthorCode())
                .name(authors.getName())
                .surname(authors.getSurname())
                .build();
    }

    private CategoriesEntity mapToCategoriesEntity(Categories categories) {
        if (categories == null) {
            return null;
        }

        return CategoriesEntity.builder()
                .categoryId(categories.getCategoryId())
                .name(categories.getName())
                .build();
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
                .phoneNumber(users.getPhoneNumber())
                .email(users.getEmail())
                .build();
    }
}
