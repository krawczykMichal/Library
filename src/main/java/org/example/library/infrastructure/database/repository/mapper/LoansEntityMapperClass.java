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
