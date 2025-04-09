package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.api.dto.CategoriesDTO;
import org.example.library.business.dao.CategoriesDao;
import org.example.library.domain.Categories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriesService {

    private final CategoriesDao categoriesDao;

    public List<Categories> findAll() {
        return categoriesDao.findAll();
    }

    public Categories findByName(String booksCategoriesName) {
        return categoriesDao.findByName(booksCategoriesName).get();
    }

    public void addCategory(CategoriesDTO categoriesDTO) {
        Categories category = Categories.builder()
                .name(categoriesDTO.getName())
                .build();

        categoriesDao.saveCategory(category);
    }

    public Categories updateCategoryName(Categories byName, CategoriesDTO categoriesDTO) {
        String updatedName = categoriesDTO.getName() != null && !categoriesDTO.getName().isEmpty()
                ? categoriesDTO.getName()
                : byName.getName();

        Categories toUpdate = byName.withName(updatedName);

        return categoriesDao.saveCategory(toUpdate);
    }
}
