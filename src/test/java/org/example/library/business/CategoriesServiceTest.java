package org.example.library.business;
import org.example.library.api.dto.CategoriesDTO;
import org.example.library.business.dao.CategoriesDao;
import org.example.library.domain.Categories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriesServiceTest {

    @Mock
    private CategoriesDao categoriesDao;

    @InjectMocks
    private CategoriesService categoriesService;

    private Categories category;
    private CategoriesDTO categoriesDTO;

    @BeforeEach
    void setUp() {
        category = Categories.builder()
                .name("Fiction")
                .build();

        categoriesDTO = CategoriesDTO.builder()
                .name("Non-Fiction")
                .build();
    }

    @Test
    void shouldFindAllCategories() {
        when(categoriesDao.findAll()).thenReturn(List.of(category));

        List<Categories> categories = categoriesService.findAll();

        assertNotNull(categories);
        assertEquals(1, categories.size());
        assertEquals("Fiction", categories.get(0).getName());
        verify(categoriesDao, times(1)).findAll();
    }

    @Test
    void shouldFindCategoryByName() {
        when(categoriesDao.findByName("Fiction")).thenReturn(Optional.of(category));

        Categories found = categoriesService.findByName("Fiction");

        assertNotNull(found);
        assertEquals("Fiction", found.getName());
        verify(categoriesDao, times(1)).findByName("Fiction");
    }

    @Test
    void shouldAddCategory() {
        Categories expectedCategory = Categories.builder()
                .name("Non-Fiction")
                .build();
        when(categoriesDao.addCategory(any(Categories.class))).thenReturn(expectedCategory);

        categoriesService.addCategory(categoriesDTO);

        verify(categoriesDao, times(1)).addCategory(argThat(cat -> cat.getName().equals("Non-Fiction")));

    }

    @Test
    void shouldUpdateCategoryNameWithoutChangeWhenDtoNameIsNonEmpty() {
        CategoriesDTO updateDto = CategoriesDTO.builder().name("Non-Fiction").build();

        Categories updatedCategory = Categories.builder().name("Fiction").build();
        when(categoriesDao.addCategory(any(Categories.class))).thenReturn(updatedCategory);

        Categories result = categoriesService.updateCategoryName(category, updateDto);

        assertNotNull(result);
        assertEquals("Fiction", result.getName());
        verify(categoriesDao, times(1)).addCategory(any(Categories.class));
    }

    @Test
    void shouldUpdateCategoryNameWhenDtoNameIsEmpty() {
        CategoriesDTO updateDto = CategoriesDTO.builder().name("").build();

        Categories updatedCategory = Categories.builder().name("").build();
        when(categoriesDao.addCategory(any(Categories.class))).thenReturn(updatedCategory);

        Categories result = categoriesService.updateCategoryName(category, updateDto);

        assertNotNull(result);
        assertEquals("", result.getName());
        verify(categoriesDao, times(1)).addCategory(any(Categories.class));
    }
}
