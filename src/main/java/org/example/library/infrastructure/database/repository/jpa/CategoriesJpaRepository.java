package org.example.library.infrastructure.database.repository.jpa;

import aj.org.objectweb.asm.commons.Remapper;
import org.example.library.domain.Categories;
import org.example.library.infrastructure.database.entity.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriesJpaRepository extends JpaRepository<CategoriesEntity, Integer> {

    @Query("select cat from CategoriesEntity cat where cat.name = :booksCategoriesName")
    Optional<CategoriesEntity> findByName(String booksCategoriesName);
}
