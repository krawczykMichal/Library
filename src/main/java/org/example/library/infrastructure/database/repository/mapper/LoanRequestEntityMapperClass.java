package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.*;
import org.example.library.infrastructure.database.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanRequestEntityMapperClass {

//    public LoanRequest mapFromLoanRequestEntity(LoanRequestEntity loanRequestEntity) {
//        if (loanRequestEntity == null) {
//            return null;
//        }
//
//        return LoanRequest.builder().build();
//    }

    public LoanRequest mapFromLoanRequestEntityFromReservation(LoanRequestEntity loanRequestEntity) {
        if (loanRequestEntity == null) {
            return null;
        }

        return LoanRequest.builder()
                .loanRequestId(loanRequestEntity.getLoanRequestId())
                .loanRequestNumber(loanRequestEntity.getLoanRequestNumber())
                .requestDate(loanRequestEntity.getRequestDate())
                .reservation(mapFromReservationEntity(loanRequestEntity.getReservation()))
                .user(mapFromUsersEntity(loanRequestEntity.getUser()))
                .loanRequestItems(mapFromLoansItemEntity(loanRequestEntity.getLoanRequestItems()))
                .build();
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
                .reservationItem(mapFromReservationItemEntity(reservationsEntity.getReservationItems()))
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
    public LoanRequest mapFromLoanRequestEntityFromCart(LoanRequestEntity loanRequestEntity) {
        if (loanRequestEntity == null) {
            return null;
        }

        return LoanRequest.builder()
                .loanRequestId(loanRequestEntity.getLoanRequestId())
                .loanRequestNumber(loanRequestEntity.getLoanRequestNumber())
                .requestDate(loanRequestEntity.getRequestDate())
                .cart(mapFromCartEntity(loanRequestEntity.getCart()))
                .user(mapFromUsersEntity(loanRequestEntity.getUser()))
                .loanRequestItems(mapFromLoansItemEntity(loanRequestEntity.getLoanRequestItems()))
                .build();
    }

    public LoanRequest mapFromLoanRequestEntity(LoanRequestEntity loanRequestEntity) {
        if (loanRequestEntity == null) {
            return null;

        }

        return LoanRequest.builder()
                .loanRequestId(loanRequestEntity.getLoanRequestId())
                .loanRequestNumber(loanRequestEntity.getLoanRequestNumber())
                .requestDate(loanRequestEntity.getRequestDate())
                .cart(mapFromCartEntity(loanRequestEntity.getCart()))
                .user(mapFromUsersEntity(loanRequestEntity.getUser()))
                .reservation(mapFromReservationEntity(loanRequestEntity.getReservation()))
                .loanRequestItems(mapFromLoansItemEntity(loanRequestEntity.getLoanRequestItems()))
                .build();
    }

    private List<LoanRequestItem> mapFromLoansItemEntity(List<LoanRequestItemEntity> loanRequestItemEntities) {
        if (loanRequestItemEntities == null) {
            return null;
        }
        return loanRequestItemEntities.stream()
                .map(entities -> LoanRequestItem.builder()
                        .loanRequestItemId(entities.getLoanRequestItemId())
                        .title(entities.getTitle())
                        .quantity(entities.getQuantity())
                        .book(mapFromBooksEntity(entities.getBooks()))
                        .build()).toList();
    }
}
