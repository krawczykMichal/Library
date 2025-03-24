package org.example.library.infrastructure.database.repository.mapper;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.UsersDao;
import org.example.library.domain.*;
import org.example.library.infrastructure.database.entity.*;
import org.example.library.infrastructure.database.repository.jpa.UsersJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class LoanRequestEntityMapperClass {

    private final UsersDao usersDao;
    private final UsersJpaRepository usersJpaRepository;



    public LoanRequest mapFromLoanRequestEntity(LoanRequestEntity loanRequestEntity) {
        if (loanRequestEntity == null) {
            return null;
        }

        return LoanRequest.builder()
                .loanRequestId(loanRequestEntity.getLoanRequestId())
                .loanRequestNumber(loanRequestEntity.getLoanRequestNumber())
                .requestDate(loanRequestEntity.getRequestDate())
                .user(mapFromUsersEntity(loanRequestEntity.getUser()))
                .loanRequestItems(mapFromLoansItemEntity(loanRequestEntity.getLoanRequestItems()))
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
                .phoneNumber(usersEntity.getPhoneNumber())
                .email(usersEntity.getEmail())
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

    public LoanRequestEntity mapToLoanRequestEntity(LoanRequest loanRequest) {
        if (loanRequest == null) {
            return null;

        }

        return LoanRequestEntity.builder()
                .loanRequestId(loanRequest.getLoanRequestId())
                .loanRequestNumber(loanRequest.getLoanRequestNumber())
                .user(findUser(loanRequest.getUser().getUsername()))
                .requestDate(loanRequest.getRequestDate())
                .build();
    }

    private UsersEntity findUser(String userName) {
        Optional<UsersEntity> byUsername = usersJpaRepository.findByUsername(userName);
        return byUsername.get();
    }
}
