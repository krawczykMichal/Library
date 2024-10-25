package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.Books;
import org.example.library.infrastructure.database.entity.BooksEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BooksEntityMapper {

    Books mapFromBooksEntity(BooksEntity booksEntity);

    BooksEntity mapToBooksEntity(Books books);
}
