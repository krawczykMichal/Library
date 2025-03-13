package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.*;
import org.example.library.infrastructure.database.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoansHistoryEntityMapperClass {

    public LoansHistory mapFromLoansHistoryEntity(LoansHistoryEntity loansHistoryEntity) {
        if (loansHistoryEntity == null) {
            return null;
        }

        return LoansHistory.builder()
                .loanHistoryId(loansHistoryEntity.getLoanHistoryId())
                .loanNumber(loansHistoryEntity.getLoanNumber())
                .loanDate(loansHistoryEntity.getLoanDate())
                .returnDate(loansHistoryEntity.getReturnDate())
                .loansHistoryItems(mapFromLoansHistoryItemEntity(loansHistoryEntity.getLoansHistoryItems()))
                .employee(mapFromEmployeesEntity(loansHistoryEntity.getEmployee()))
                .user(mapFromUsersEntity(loansHistoryEntity.getUser()))
                .build();
    }

    private List<LoansHistoryItem> mapFromLoansHistoryItemEntity(List<LoansHistoryItemEntity> loansHistoryItems) {
        if (loansHistoryItems == null) {
            return null;
        }

        return loansHistoryItems.stream()
                .map(entities -> LoansHistoryItem.builder()
                        .loansHistoryItemId(entities.getLoansHistoryItemId())
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
}
