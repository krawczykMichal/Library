package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.*;
import org.example.library.infrastructure.database.entity.*;
import org.springframework.stereotype.Component;

@Component
public class LoanItemEntityMapperClass {

    public LoanItem mapFromLoanItemEntity(LoanItemEntity loanItemEntity) {
        if (loanItemEntity == null) {
            return null;
        }

        return LoanItem.builder()
                .loanItemId(loanItemEntity.getLoanItemId())
                .title(loanItemEntity.getTitle())
                .quantity(loanItemEntity.getQuantity())
                .book(mapFromBooksEntity(loanItemEntity.getBook()))
                .build();
    }

    private Books mapFromBooksEntity(BooksEntity booksEntity) {
        if (booksEntity == null) {
            return null;
        }

        return Books.builder()
                .bookId(booksEntity.getBookId())
                .category(mapFromCategoriesEntity(booksEntity.getCategory()))
                .author(mapFromAuthorsEntity(booksEntity.getAuthor()))
                .isbn(booksEntity.getIsbn())
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

    private Cart mapFromCartEntity(CartEntity cartEntity) {
        if (cartEntity == null) {
            return null;
        }

        return Cart.builder()
                .cartId(cartEntity.getCartId())
                .build();
    }
}
