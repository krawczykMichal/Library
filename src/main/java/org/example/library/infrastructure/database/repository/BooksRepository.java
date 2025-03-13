package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.BooksDao;
import org.example.library.domain.Books;
import org.example.library.infrastructure.database.entity.BooksEntity;
import org.example.library.infrastructure.database.repository.jpa.BooksJpaRepository;
import org.example.library.infrastructure.database.repository.mapper.BooksEntityMapper;
import org.example.library.infrastructure.database.repository.mapper.BooksEntityMapperClass;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class BooksRepository implements BooksDao {

    private final BooksJpaRepository booksJpaRepository;

    private final BooksEntityMapper booksEntityMapper;

    private final BooksEntityMapperClass booksEntityMapperClass;

    @Override
    public List<Books> findAll() {
        return booksJpaRepository.findAll().stream().map(booksEntityMapperClass::mapFromBooksEntity).toList();
    }

    @Override
    public Books saveBook(Books book) {
        BooksEntity toSave = booksEntityMapper.mapToBooksEntity(book);
        BooksEntity saved = booksJpaRepository.save(toSave);
        return booksEntityMapper.mapFromBooksEntity(saved);
    }

    @Override
    public Optional<Books> findByIsbn(String isbn) {
        return booksJpaRepository.findByIsbn(isbn).map(booksEntityMapperClass::mapFromBooksEntity);
    }

    @Override
    public List<Books> findByTitleInclude(String title) {
        return booksJpaRepository.findByTitleInclude(title);
    }
}
