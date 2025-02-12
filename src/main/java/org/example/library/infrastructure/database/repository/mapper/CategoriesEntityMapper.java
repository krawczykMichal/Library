package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.Categories;
import org.example.library.infrastructure.database.entity.CategoriesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoriesEntityMapper {

//    @Mapping(target = "books", ignore = true)
    Categories mapFromCategoriesEntity(CategoriesEntity categoriesEntity);

    CategoriesEntity mapToCategoriesEntity(Categories categories);
}
