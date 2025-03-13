package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.Categories;
import org.example.library.infrastructure.database.entity.CategoriesEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryEntityMapperClass {

    public Categories mapFromCategoryEntity(CategoriesEntity categoriesEntity) {
        if (categoriesEntity == null) {
            return null;
        }

        return Categories.builder()
                .categoryId(categoriesEntity.getCategoryId())
                .name(categoriesEntity.getName())
                .build();
    }
}
