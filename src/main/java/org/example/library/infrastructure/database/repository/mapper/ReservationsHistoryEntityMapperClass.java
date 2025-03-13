package org.example.library.infrastructure.database.repository.mapper;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.BooksDao;
import org.example.library.business.dao.UsersDao;
import org.example.library.domain.*;
import org.example.library.infrastructure.database.entity.*;
import org.example.library.infrastructure.database.repository.jpa.BooksJpaRepository;
import org.example.library.infrastructure.database.repository.jpa.UsersJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ReservationsHistoryEntityMapperClass {

    private final BooksDao booksDao;
    private final BooksJpaRepository booksJpaRepository;
    private final UsersDao usersDao;
    private final UsersJpaRepository usersJpaRepository;


    public ReservationsHistory mapFromReservationsHistoryEntity(ReservationsHistoryEntity reservationsHistoryEntity) {
        if (reservationsHistoryEntity == null) {
            return null;
        }

        return ReservationsHistory.builder()
                .reservationHistoryId(reservationsHistoryEntity.getReservationHistoryId())
                .reservationNumber(reservationsHistoryEntity.getReservationNumber())
                .reservationMakeDate(reservationsHistoryEntity.getReservationMakeDate())
                .reservationHoldToDate(reservationsHistoryEntity.getReservationHoldToDate())
                .reservationHistoryItems(mapFromReservationsHistoryItemEntity(reservationsHistoryEntity.getReservationHistoryItems()))
                .user(mapFromUsersEntity(reservationsHistoryEntity.getUsers()))
                .build();
    }

    private List<ReservationsHistoryItem> mapFromReservationsHistoryItemEntity(List<ReservationsHistoryItemEntity> reservationsHistoryItemEntity) {
        if (reservationsHistoryItemEntity == null) {
            return null;
        }

        return reservationsHistoryItemEntity.stream()
                .map(entities -> ReservationsHistoryItem.builder()
                        .reservationHistoryItemId(entities.getReservationHistoryItemId())
                        .title(entities.getTitle())
                        .quantity(entities.getQuantity())
                        .book(mapFromBooksEntity(entities.getBook()))
                        .build()).toList();
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

    public ReservationsHistoryEntity mapToReservationsEntity(ReservationsHistory reservationsHistory) {
        if (reservationsHistory == null) {
            return null;
        }

        return ReservationsHistoryEntity.builder()
                .reservationHistoryId(reservationsHistory.getReservationHistoryId())
                .reservationNumber(reservationsHistory.getReservationNumber())
                .reservationMakeDate(reservationsHistory.getReservationMakeDate())
                .reservationHoldToDate(reservationsHistory.getReservationHoldToDate())
                .reservationHistoryItems(mapToReservationsHistoryItemEntity(reservationsHistory.getReservationHistoryItems()))
                .users(findUserById(reservationsHistory.getUser().getUserId()))
                .build();
    }

    private List<ReservationsHistoryItemEntity> mapToReservationsHistoryItemEntity(List<ReservationsHistoryItem> reservationsHistoryItem) {
        if (reservationsHistoryItem == null) {
            return null;
        }

        return reservationsHistoryItem.stream()
                .map(entities -> ReservationsHistoryItemEntity.builder()
                        .reservationHistoryItemId(entities.getReservationHistoryItemId())
                        .title(entities.getTitle())
                        .quantity(entities.getQuantity())
                        .book(findBook(entities.getBook().getIsbn()))
                        .build()).toList();
    }

    private BooksEntity findBook(String isbn) {
//        Optional<Books> byIsbn = booksDao.findByIsbn(isbn);
//        Books books = byIsbn.get();
//        System.out.println("Books: " + books);

        Optional<BooksEntity> booksEntityOptional = booksJpaRepository.findByIsbn(isbn);
        BooksEntity booksEntity = booksEntityOptional.get();



        return booksEntity;

    }

    private UsersEntity findUserById(Integer userId) {
//        Optional<Users> byId = usersDao.findById(userId);
//        Users users = byId.get();

        Optional<UsersEntity> byId1 = usersJpaRepository.findById(userId);
        return byId1.get();
    }
}
