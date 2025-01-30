package org.example.library.business.dao;

import org.example.library.domain.Authors;

import java.util.List;
import java.util.Optional;

public interface AuthorsDao {

    Authors saveAuthor(Authors author);

    Optional<Authors> findById(Integer authorId);

    List<Authors> findAll();

    Optional<Authors> findByAuthorCode(String booksAuthorCode);
}
