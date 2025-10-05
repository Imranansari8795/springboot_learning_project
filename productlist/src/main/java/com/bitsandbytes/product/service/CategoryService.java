package com.bitsandbytes.product.service;


import com.bitsandbytes.product.dto.CategoryDTO;
import com.bitsandbytes.product.entity.Category;
import com.bitsandbytes.product.mapper.CategoryMapper;
import com.bitsandbytes.product.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {


    private CategoryRepository categoryRepository;
    // create category

    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        Category category = CategoryMapper.toCategoryEntity(categoryDTO);
        category = categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);
    }
    // get all categories
    // get category by id;
    // delete category
}
