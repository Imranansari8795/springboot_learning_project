package com.bitsandbytes.product.controller;

import com.bitsandbytes.product.dto.ProductDTO;
import com.bitsandbytes.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(
        name = "Product REST API CRUD operation",
        description = "CREATE READ UPDATE DELETE operations for product REST API"
)
@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    // get all products

    @Operation(
            summary = "Fetch all Products",
            description = "REST API to fetch all products."
    )
    @GetMapping
    public List<ProductDTO> getAllProduct(){
        return productService.getAllProducts();
    }
    // create products

    @Operation(
            summary = "Create Product",
            description = "REST API to create product"
    )
    @ApiResponse(
            responseCode = "201",
            description = "CREATED"
    )

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        ProductDTO createProduct = productService.createProduct(productDTO);
        return new ResponseEntity<>(createProduct, HttpStatus.CREATED);
    }
    // update product


    @Operation(
            summary = "Update product by Product Id",
            description = "REST API to update product by Id"
    )


    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        return productService.updateProduct(id,productDTO);
    }
    // get product by id
    @Operation(
            summary = "Fetch product by product Id",
            description = "REST API to fetch product by Product Id"
    )
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    // delete product

    @Operation(
            summary = "Delete product by product Id",
            description = "REST API to delete product by product Id"
    )

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){

        return productService.deleteProductById(id);
    }

}
