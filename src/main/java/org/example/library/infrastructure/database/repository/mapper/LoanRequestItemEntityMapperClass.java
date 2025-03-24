package org.example.library.infrastructure.database.repository.mapper;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.BooksDao;
import org.example.library.business.dao.LoanRequestDao;
import org.example.library.domain.*;
import org.example.library.infrastructure.database.entity.*;
import org.example.library.infrastructure.database.repository.jpa.BooksJpaRepository;
import org.example.library.infrastructure.database.repository.jpa.LoanRequestJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class LoanRequestItemEntityMapperClass {

    private final BooksDao booksDao;
    private final BooksJpaRepository booksJpaRepository;
    private final LoanRequestDao loanRequestDao;
    private final LoanRequestJpaRepository loanRequestJpaRepository;

    public LoanRequestItem mapFromLoanRequestItemEntity(LoanRequestItemEntity loanRequestItemEntity) {
        if (loanRequestItemEntity == null) {
            return null;
        }

        return LoanRequestItem.builder()
                .loanRequestItemId(loanRequestItemEntity.getLoanRequestItemId())
                .title(loanRequestItemEntity.getTitle())
                .quantity(loanRequestItemEntity.getQuantity())
                .book(mapFromBooksEntity(loanRequestItemEntity.getBooks()))
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
                .publisher(booksEntity.getPublisher())
                .publishedDate(booksEntity.getPublishedDate())
                .copies(booksEntity.getCopies())
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

    public LoanRequestItemEntity mapToLoanRequestItemEntity(LoanRequestItem loanRequestItem) {
        if (loanRequestItem == null) {
            return null;

        }

        return LoanRequestItemEntity.builder()
                .books(findBook(loanRequestItem.getBook().getIsbn()))
                .title(loanRequestItem.getTitle())
                .quantity(loanRequestItem.getQuantity())
                .loanRequest(findLoanRequest(loanRequestItem.getLoanRequest().getLoanRequestNumber()))
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

    private LoanRequestEntity findLoanRequest(String loanRequestNumber) {
        Optional<LoanRequest> byLoanRequestNumber = loanRequestDao.findByLoanRequestNumber(loanRequestNumber);
        LoanRequest loanRequest = byLoanRequestNumber.get();

        System.out.println("LoanRequest: " + loanRequest);

        Optional<LoanRequestEntity> byLoanRequestNumber1 = loanRequestJpaRepository.findByLoanRequestNumber(loanRequestNumber);
        return byLoanRequestNumber1.get();
    }
}
