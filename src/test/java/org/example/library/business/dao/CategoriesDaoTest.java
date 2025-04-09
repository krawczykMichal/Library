package org.example.library.business.dao;

import org.example.library.domain.Categories;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest
@TestPropertySource(properties = "spring.flyway.enabled=false") // Wyłączenie migracji Flyway
public class CategoriesDaoTest {

    @MockBean
    private CategoriesDao categoriesDao;

    @Test
    void testFindAll() {
        // Tworzenie kategorii za pomocą buildera
        Categories category1 = Categories.builder().name("Fiction").build();
        Categories category2 = Categories.builder().name("Science").build();

        List<Categories> categoriesList = Arrays.asList(category1, category2);

        // Mockowanie findAll
        when(categoriesDao.findAll()).thenReturn(categoriesList);

        // Wywołanie metody findAll
        List<Categories> foundCategories = categoriesDao.findAll();

        // Sprawdzanie wyników
        assertFalse(foundCategories.isEmpty());
        assertEquals(2, foundCategories.size());
        assertEquals("Fiction", foundCategories.get(0).getName());
        assertEquals("Science", foundCategories.get(1).getName());
    }

    @Test
    void testFindByName() {
        // Tworzenie kategorii za pomocą buildera
        Categories category = Categories.builder().name("History").build();

        // Mockowanie findByName
        when(categoriesDao.findByName("History")).thenReturn(Optional.of(category));

        // Wywołanie metody findByName
        Optional<Categories> foundCategory = categoriesDao.findByName("History");

        // Sprawdzanie wyników
        assertTrue(foundCategory.isPresent());
        assertEquals("History", foundCategory.get().getName());
    }

    @Test
    void testFindByNameNotFound() {
        // Mockowanie findByName dla nieistniejącej kategorii
        when(categoriesDao.findByName("Nonexistent"))
                .thenReturn(Optional.empty());

        // Wywołanie metody findByName
        Optional<Categories> foundCategory = categoriesDao.findByName("Nonexistent");

        // Sprawdzanie, że kategoria nie została znaleziona
        assertFalse(foundCategory.isPresent());
    }

    @Test
    void testSaveCategory() {
        // Tworzenie nowej kategorii za pomocą buildera
        Categories category = Categories.builder().name("Philosophy").build();

        // Mockowanie addCategory
        when(categoriesDao.saveCategory(any(Categories.class))).thenReturn(category);

        // Wywołanie metody addCategory
        Categories addedCategory = categoriesDao.saveCategory(category);

        // Sprawdzanie wyników
        assertNotNull(addedCategory);
        assertEquals("Philosophy", addedCategory.getName());
    }
}