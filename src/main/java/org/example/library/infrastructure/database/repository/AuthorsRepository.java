package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.AuthorsDao;
import org.example.library.domain.Authors;
import org.example.library.infrastructure.database.entity.AuthorsEntity;
import org.example.library.infrastructure.database.repository.jpa.AuthorsJpaRepository;
import org.example.library.infrastructure.database.repository.mapper.AuthorsEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class AuthorsRepository implements AuthorsDao {

    private final AuthorsJpaRepository authorsJpaRepository;

    private final AuthorsEntityMapper authorsEntityMapper;

    @Override
    public Authors saveAuthor(Authors author) {
        AuthorsEntity toSave = authorsEntityMapper.mapToAuthorsEntity(author);
        AuthorsEntity saved = authorsJpaRepository.save(toSave);
        return authorsEntityMapper.mapFromAuthorsEntity(saved);
    }

    @Override
    public Optional<Authors> findById(Integer authorId) {
        return authorsJpaRepository.findById(authorId).map(authorsEntityMapper::mapFromAuthorsEntity);
    }

    @Override
    public List<Authors> findAll() {
        return authorsJpaRepository.findAll().stream().map(authorsEntityMapper::mapFromAuthorsEntity).toList();
    }

    @Override
    public Optional<Authors> findByAuthorCode(String booksAuthorCode) {
        return authorsJpaRepository.findByAuthorCode(booksAuthorCode).map(authorsEntityMapper::mapFromAuthorsEntity);
    }
}
