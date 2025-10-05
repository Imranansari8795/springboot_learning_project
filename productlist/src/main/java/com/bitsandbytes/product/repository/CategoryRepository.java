package com.bitsandbytes.product.repository;

import com.bitsandbytes.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
