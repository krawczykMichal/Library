package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.Authors;
import org.example.library.domain.Books;
import org.example.library.domain.Categories;
import org.example.library.infrastructure.database.entity.AuthorsEntity;
import org.example.library.infrastructure.database.entity.BooksEntity;
import org.example.library.infrastructure.database.entity.CategoriesEntity;
import org.springframework.stereotype.Component;

@Component
public class BooksEntityMapperClass {

    public Books mapFromBooksEntity(BooksEntity booksEntity) {
        if (booksEntity == null) {
            return null;
        }

        return Books.builder()
                .title(booksEntity.getTitle())
                .isbn(booksEntity.getIsbn())
                .publisher(booksEntity.getPublisher())
                .publishedDate(booksEntity.getPublishedDate())
                .copies(booksEntity.getCopies())
                .available(booksEntity.getAvailable())
                .author(mapFromAuthorsEntity(booksEntity.getAuthor()))
                .category(mapFromCategoriesEntity(booksEntity.getCategory()))
                .build();
    }

    private Authors mapFromAuthorsEntity(AuthorsEntity authorsEntity) {
        if (authorsEntity == null) {
            return null;
        }

        return Authors.builder()
                .name(authorsEntity.getName())
                .surname(authorsEntity.getSurname()).build();
    }

    private Categories mapFromCategoriesEntity(CategoriesEntity categoriesEntity) {
        if (categoriesEntity == null) {
            return null;
        }

        return Categories.builder()
                .name(categoriesEntity.getName()).build();
    }
}
