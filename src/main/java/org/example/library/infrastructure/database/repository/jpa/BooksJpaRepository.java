package org.example.library.infrastructure.database.repository.jpa;

import org.example.library.domain.Books;
import org.example.library.infrastructure.database.entity.BooksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksJpaRepository extends JpaRepository<BooksEntity, Integer> {

    @Query("select boo from BooksEntity boo where boo.isbn = :isbn")
    Optional<BooksEntity> findByIsbn(String isbn);

    @Query("select boo from BooksEntity boo where LOWER(boo.title) like lower(concat('%', :title, '%'))")
    List<Books> findByTitleInclude(String title);
}
