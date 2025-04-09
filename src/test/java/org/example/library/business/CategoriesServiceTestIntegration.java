package org.example.library.business;

import org.example.library.api.dto.CategoriesDTO;
import org.example.library.business.dao.CategoriesDao;
import org.example.library.domain.Categories;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class CategoriesServiceTestIntegration {

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private CategoriesDao categoriesDao;

    @Autowired
    private Flyway flyway;

    private Categories sampleCategory;

    @BeforeEach
    void setUp() {
        flyway.clean();
        flyway.migrate();

        sampleCategory = Categories.builder()
                .name("Fictions")
                .build();
        categoriesDao.saveCategory(sampleCategory);
    }

    @Test
    void testFindAll() {
        // When
        List<Categories> categories = categoriesService.findAll();

        // Then
        assertNotNull(categories);
        assertFalse(categories.isEmpty());
        assertTrue(categories.stream().anyMatch(c -> "Fictions".equals(c.getName())));
    }

    @Test
    void testFindByName() {
        // When
        Categories foundCategory = categoriesService.findByName("Fictions");

        // Then
        assertNotNull(foundCategory);
        assertEquals("Fictions", foundCategory.getName());
    }

    @Test
    void testAddCategory() {
        // Given
        CategoriesDTO newCategoryDTO = CategoriesDTO.builder()
                .name("Science")
                .build();

        // When
        categoriesService.addCategory(newCategoryDTO);

        // Then
        Categories foundCategory = categoriesService.findByName("Science");
        assertNotNull(foundCategory);
        assertEquals("Science", foundCategory.getName());
    }

    @Test
    void testUpdateCategoryName() {
        // Given
        Categories category = Categories.builder()
                .name("Fictiones").build();
        CategoriesDTO updateDTO = CategoriesDTO.builder()
                .name("Updated Fiction")
                .build();

        // When
        Categories updatedCategory = categoriesService.updateCategoryName(category, updateDTO);

        // Then
        assertNotNull(updatedCategory);
        assertEquals("Updated Fiction", updatedCategory.getName());
    }
}