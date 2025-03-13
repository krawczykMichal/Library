package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.api.dto.CategoriesDTO;
import org.example.library.business.dao.CategoriesDao;
import org.example.library.domain.Categories;
import org.example.library.infrastructure.database.entity.CategoriesEntity;
import org.example.library.infrastructure.database.repository.jpa.CategoriesJpaRepository;
import org.example.library.infrastructure.database.repository.mapper.CategoriesEntityMapper;
import org.example.library.infrastructure.database.repository.mapper.CategoryEntityMapperClass;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CategoriesRepository implements CategoriesDao {

    private final CategoriesJpaRepository categoriesJpaRepository;
    private final CategoriesEntityMapper categoriesEntityMapper;
    private final CategoryEntityMapperClass categoryEntityMapperClass;

    @Override
    public List<Categories> findAll() {
        return categoriesJpaRepository.findAll().stream().map(categoryEntityMapperClass::mapFromCategoryEntity).toList();
    }

    @Override
    public Optional<Categories> findByName(String booksCategoriesName) {
        return categoriesJpaRepository.findByName(booksCategoriesName).map(categoryEntityMapperClass::mapFromCategoryEntity);
    }

    @Override
    public Categories addCategory(Categories category) {
        CategoriesEntity toSave = categoriesEntityMapper.mapToCategoriesEntity(category);
        CategoriesEntity saved = categoriesJpaRepository.save(toSave);
        return categoriesEntityMapper.mapFromCategoriesEntity(saved);
    }
}
