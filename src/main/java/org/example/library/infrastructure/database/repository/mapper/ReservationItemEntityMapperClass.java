package org.example.library.infrastructure.database.repository.mapper;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.BooksDao;
import org.example.library.business.dao.ReservationsDao;
import org.example.library.domain.*;
import org.example.library.infrastructure.database.entity.*;
import org.example.library.infrastructure.database.repository.jpa.BooksJpaRepository;
import org.example.library.infrastructure.database.repository.jpa.ReservationsJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ReservationItemEntityMapperClass {

    private final BooksDao booksDao;
    private final BooksJpaRepository booksJpaRepository;
    private final ReservationsDao reservationsDao;
    private final ReservationsJpaRepository reservationsJpaRepository;

    public ReservationItem mapFromReservationItemEntity(ReservationItemEntity reservationItemEntity) {
        if (reservationItemEntity == null) {
            return null;
        }

        return ReservationItem.builder()
                .reservationItemId(reservationItemEntity.getReservationItemId())
                .title(reservationItemEntity.getTitle())
                .quantity(reservationItemEntity.getQuantity())
                .book(mapFromBooksEntity(reservationItemEntity.getBook()))
                .reservation(mapFromReservationEntity(reservationItemEntity.getReservation()))
                .build();
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

    private Reservations mapFromReservationEntity(ReservationsEntity reservationsEntity) {
        if (reservationsEntity == null) {
            return null;

        }

        return Reservations.builder()
                .reservationId(reservationsEntity.getReservationsId())
                .reservationNumber(reservationsEntity.getReservationNumber())
                .reservationMakeDate(reservationsEntity.getReservationMakeDate())
                .reservationHoldToDate(reservationsEntity.getReservationHoldToDate())
                .cart(mapFromCartEntity(reservationsEntity.getCart()))
                .build();
    }
    private Cart mapFromCartEntity(CartEntity cartEntity) {
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
                        .book(mapFromBooksEntity(entities.getBook()))
                        .quantity(entities.getQuantity())
                        .build()
                )
                .toList();
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

    public ReservationItemEntity mapToReservationItemEntity(ReservationItem reservationItem) {
        if (reservationItem == null) {
            return null;
        }

        return ReservationItemEntity.builder()
                .reservationItemId(reservationItem.getReservationItemId())
                .reservation(findReservation(reservationItem.getReservation().getReservationNumber()))
                .book(findBook(reservationItem.getBook().getIsbn()))
                .title(reservationItem.getTitle())
                .quantity(reservationItem.getQuantity())
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

    private ReservationsEntity findReservation(String reservationNumber) {
        Optional<Reservations> byReservationNumber = reservationsDao.findByReservationNumber(reservationNumber);
        Reservations reservations = byReservationNumber.get();
        System.out.println("Reservations: " + reservations);

        Optional<ReservationsEntity> reservationsEntityOptional = reservationsJpaRepository.findByReservationNumber(reservationNumber);
        ReservationsEntity reservationsEntity = reservationsEntityOptional.get();
        return reservationsEntity;
    }

}
