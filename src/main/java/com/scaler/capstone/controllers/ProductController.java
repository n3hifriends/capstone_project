package com.scaler.capstone.controllers;

import com.scaler.capstone.dtos.FakeStoreProductDto;
import com.scaler.capstone.dtos.ProductDto;
import com.scaler.capstone.models.Category;
import com.scaler.capstone.models.Product;
import com.scaler.capstone.models.State;
import com.scaler.capstone.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return products.stream().map(this::toProductDto).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable Long id) {
        try {

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            if (id <= 0) {
                throw new IllegalArgumentException("Invalid product id"); // thrown from ControllerAdvice, it will call automatically
            }
            Product product = productService.getProductById(id);
            if (product == null) {
                throw new IllegalArgumentException("Invalid product id"); // thrown from ControllerAdvice, it will call automatically
            }
            params.add("called by", "Intelligent");
            return new ResponseEntity<>(toProductDto(product), params, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid product id"); // thrown from ControllerAdvice, it will call automatically
        } catch (Exception e) {
        }

        return null;
    }

    public ProductDto toProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setCategory("" + product.getCategory());
        productDto.setImage(product.getImageUrl());
        return productDto;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {

        return product;
    }

    @PutMapping("/{id}")
    public ProductDto replaceProduct(@PathVariable Long id, @RequestBody ProductDto request) {
        Product product = productService.replaceProduct(id, createProduct(request));
        return toProductDto(product);
    }

    private Product createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(new Category());
        product.setImageUrl(productDto.getImage());
        return product;
    }
}
