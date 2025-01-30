package org.example.library.infrastructure.database.repository.jpa;

import org.example.library.domain.Authors;
import org.example.library.infrastructure.database.entity.AuthorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorsJpaRepository extends JpaRepository<AuthorsEntity, Integer> {

    @Query("select au from AuthorsEntity au where au.authorCode = :booksAuthorCode")
    Optional<AuthorsEntity> findByAuthorCode(String booksAuthorCode);
}
