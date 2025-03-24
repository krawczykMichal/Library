package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.*;
import org.example.library.infrastructure.database.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationsEntityMapperClass {

    public Reservations mapFromReservationsEntity(ReservationsEntity reservationsEntity) {
        if (reservationsEntity == null) {
            return null;
        }

        return Reservations.builder()
                .reservationId(reservationsEntity.getReservationsId())
                .reservationNumber(reservationsEntity.getReservationNumber())
                .reservationMakeDate(reservationsEntity.getReservationMakeDate())
                .reservationHoldToDate(reservationsEntity.getReservationHoldToDate())
                .reservationItem(mapFromReservationItemEntity(reservationsEntity.getReservationItems()))
                .build();
    }

    private List<ReservationItem> mapFromReservationItemEntity(List<ReservationItemEntity> reservationItemEntity) {
        if (reservationItemEntity == null) {
            return null;
        }

        return reservationItemEntity.stream()
                .map(entities -> ReservationItem.builder()
                        .reservationItemId(entities.getReservationItemId())
                        .book(mapFromBooksEntity(entities.getBook()))
                        .title(entities.getTitle())
                        .quantity(entities.getQuantity())
                        .build()).toList();
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
                .publishedDate(booksEntity.getPublishedDate())
                .publisher(booksEntity.getPublisher())
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

    public ReservationsEntity mapToReservationsEntity(Reservations reservation) {
        if (reservation == null) {
            return null;
        }

        return ReservationsEntity.builder()
                .reservationsId(reservation.getReservationId())
                .reservationNumber(reservation.getReservationNumber())
                .reservationHoldToDate(reservation.getReservationHoldToDate())
                .reservationMakeDate(reservation.getReservationMakeDate())
                .cart(mapToCartEntity(reservation.getCart()))
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
                .book(mapToBooksEntity(entities.getBook()))
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
