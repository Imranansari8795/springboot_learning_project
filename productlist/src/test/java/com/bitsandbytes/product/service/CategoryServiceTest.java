package com.bitsandbytes.product.service;

import com.bitsandbytes.product.dto.CategoryDTO;
import com.bitsandbytes.product.entity.Category;
import com.bitsandbytes.product.exception.CategoryAlreadyExistsException;
import com.bitsandbytes.product.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;
    private CategoryDTO categoryDTO;


    @BeforeEach
    void setUp() {
        category = new Category();
        category.setName("test");
        category.setId(1L);
        categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("test");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createCategory_categoryShouldBeCreated() {
        when(categoryRepository.findByName(categoryDTO.getName())).thenReturn(Optional.empty());
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        CategoryDTO saveCategory = categoryService.createCategory(categoryDTO);
        assertNotNull(saveCategory);
        assertEquals(categoryDTO.getName(),saveCategory.getName());
    }

    @Test
    void createCategory_ShouldThrowException_WhenCategoryExists(){
        when(categoryRepository.findByName(categoryDTO.getName())).thenReturn(Optional.of(category));
        assertThrows(CategoryAlreadyExistsException.class, ()-> categoryService.createCategory(categoryDTO));
        verify(categoryRepository,times(0)).save(any(Category.class));
    }

//    @Test
//    void getAllCategories() {
//    }
//
//    @Test
//    void getCategoryById() {
//    }
//
//    @Test
//    void deleteCategory() {
//    }
}