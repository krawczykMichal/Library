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
}
