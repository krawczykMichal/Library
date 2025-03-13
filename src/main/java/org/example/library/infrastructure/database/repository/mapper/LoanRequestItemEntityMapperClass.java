package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.Authors;
import org.example.library.domain.Books;
import org.example.library.domain.Categories;
import org.example.library.domain.LoanRequestItem;
import org.example.library.infrastructure.database.entity.AuthorsEntity;
import org.example.library.infrastructure.database.entity.BooksEntity;
import org.example.library.infrastructure.database.entity.CategoriesEntity;
import org.example.library.infrastructure.database.entity.LoanRequestItemEntity;
import org.springframework.stereotype.Component;

@Component
public class LoanRequestItemEntityMapperClass {

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
}
