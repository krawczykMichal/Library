package org.example.library.business.dao;

import org.example.library.domain.Books;

import java.util.List;
import java.util.Optional;

public interface BooksDao {

    List<Books> findAll();

    Books saveBook(Books book);

    Optional<Books> findByIsbn(String isbn);

    List<Books> findByTitleInclude(String title);
}
