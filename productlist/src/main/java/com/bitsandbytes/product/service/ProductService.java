package com.bitsandbytes.product.service;



import com.bitsandbytes.product.dto.ProductDTO;
import com.bitsandbytes.product.entity.Category;
import com.bitsandbytes.product.entity.Product;
import com.bitsandbytes.product.mapper.ProductMapper;
import com.bitsandbytes.product.repository.CategoryRepository;
import com.bitsandbytes.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductDTO createProduct(ProductDTO productDTO){
        /****
         * name , description , price , categoryId
         * *****/
        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found!"));

        // DTP --> entity
        Product product = ProductMapper.toProductEntity(productDTO, category);
        product = productRepository.save(product);

        //entity to DTO
        return ProductMapper.toProductDTO(product);
    }

    public List<ProductDTO> getAllProducts(){
        return productRepository.findAll().stream().map(ProductMapper::toProductDTO).toList();
    }

    // get product by id
    public ProductDTO getProductById(Long id){
        Product product = productRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
        return ProductMapper.toProductDTO(product);
    }

    // update product

    public ProductDTO updateProduct(Long id, ProductDTO productDTO){
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found!"));
        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found!"));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        productRepository.save(product);
        return ProductMapper.toProductDTO(product);
    }

    // delete product by id

    public String deleteProductById(Long id){
        productRepository.deleteById(id);
        return "Product "+id+" has been deleted!";
    }

}
