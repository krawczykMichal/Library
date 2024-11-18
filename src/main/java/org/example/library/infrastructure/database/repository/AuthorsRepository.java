package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.AuthorsDao;
import org.example.library.domain.Authors;
import org.example.library.infrastructure.database.entity.AuthorsEntity;
import org.example.library.infrastructure.database.repository.jpa.AuthorsJpaRepository;
import org.example.library.infrastructure.database.repository.mapper.AuthorsEntityMapper;
import org.springframework.stereotype.Repository;

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
}
