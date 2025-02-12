package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.*;
import org.example.library.infrastructure.database.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoansEntityMapperClass {

    public Loans mapFromLoansEntityForReservations(LoansEntity loansEntity) {
        if (loansEntity == null) {
            return null;
        }

        return Loans.builder()
                .loanId(loansEntity.getLoanId())
                .loanNumber(loansEntity.getLoanNumber())
                .loanDate(loansEntity.getLoanDate())
                .returnDate(loansEntity.getReturnDate())
                .returned(loansEntity.getReturned())
                .loanItem(mapFromLoansItemEntity(loansEntity.getLoanItems()))
                .user(mapFromUsersEntity(loansEntity.getUser()))
                .employee(mapFromEmployeesEntity(loansEntity.getEmployee()))
                .loanRequest(mapFromLoansRequestEntity(loansEntity.getLoanRequest()))
                .build();
    }

    private Users mapFromUsersEntity(UsersEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        return Users.builder()
                .userId(userEntity.getUserId())
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .phoneNumber(userEntity.getPhoneNumber())
                .build();
    }

    private Employees mapFromEmployeesEntity(EmployeesEntity employeesEntity) {
        if (employeesEntity == null) {
            return null;
        }

        return Employees.builder()
                .employeeId(employeesEntity.getEmployeeId())
                .name(employeesEntity.getName())
                .surname(employeesEntity.getSurname())
                .employeeNumber(employeesEntity.getEmployeeNumber())
                .build();
    }

    private LoanRequest mapFromLoansRequestEntity(LoanRequestEntity loansRequestEntity) {
        if (loansRequestEntity == null) {
            return null;
        }

        return LoanRequest.builder()
                .loanRequestId(loansRequestEntity.getLoanRequestId())
                .requestDate(loansRequestEntity.getRequestDate())
                .reservation(mapFromReservationsEntity(loansRequestEntity.getReservation())).build();
    }

    private List<LoanItem> mapFromLoansItemEntity(List<LoanItemEntity> loansItemEntity) {
        if (loansItemEntity == null) {
            return null;
        }
        return loansItemEntity.stream()
                .map(entities -> LoanItem.builder()
                        .loanItemId(entities.getLoanItemId())
                        .title(entities.getTitle())
                        .book(mapFromBooksEntity(entities.getBook()))
                        .quantity(entities.getQuantity())
                        .build()).toList();
    }

    private Reservations mapFromReservationsEntity(ReservationsEntity reservationsEntity) {
        if (reservationsEntity == null) {
            return null;
        }

        return Reservations.builder()
                .reservationId(reservationsEntity.getReservationsId())
                .reservationMakeDate(reservationsEntity.getReservationMakeDate())
                .reservationHoldToDate(reservationsEntity.getReservationHoldToDate())
                .cart(mapFromCartEntity(reservationsEntity.getCart())).build();
    }

    private Cart mapFromCartEntity(CartEntity cartEntity) {
        if (cartEntity == null) {
            return null;
        }

        return Cart.builder()
                .cartId(cartEntity.getCartId())
                .cartItem(mapFromCartItemEntity(cartEntity.getCartItems()))
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
}
