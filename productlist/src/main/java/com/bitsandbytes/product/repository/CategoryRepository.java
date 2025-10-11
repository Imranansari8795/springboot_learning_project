package com.bitsandbytes.product.repository;

import com.bitsandbytes.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String categoryName);
    void deleteByName(String categoryName);
}


