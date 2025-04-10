package org.example.library.business.dao;

import org.example.library.api.dto.CategoriesDTO;
import org.example.library.domain.Categories;

import java.util.List;
import java.util.Optional;

public interface CategoriesDao {

    List<Categories> findAll();

    Optional<Categories> findByName(String booksCategoriesName);

    Categories addCategory(Categories category);
}
