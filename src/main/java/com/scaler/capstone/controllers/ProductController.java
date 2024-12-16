package com.scaler.capstone.controllers;

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

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        Product product = new Product();
        product.setId(1L);
        product.setName("iPhone");
        product.setCategory(new Category());
        product.setDescription("dfadf");
        product.setImageUrl("dfasd");
        product.setPrice(3423);
        product.setCreatedAt(new Date());
        product.setLastUpdatedAt(new Date());
        product.setState(State.ACTIVE);

        List<Product> products = new ArrayList<>();
        products.add(product);
        return products;
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable Long id) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if(id <= 0){
            params.add("called by", "bhudwak");
            return new ResponseEntity<>(null, params, HttpStatus.BAD_REQUEST);
//            return ResponseEntity.badRequest().headers(params).build();
        }
        Product product = productService.getProductById(id);
        if( product == null){
            return new ResponseEntity<>(null, params, HttpStatus.NOT_FOUND);

//            return ResponseEntity.notFound().build();
        }
        params.add("called by", "Intelligent");
        return new ResponseEntity<>(null, params, HttpStatus.OK);

//        return ResponseEntity.ok(toProductDto(product));
    }

    public static ProductDto toProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(""+product.getCategory());
        productDto.setImage(product.getImageUrl());
        return productDto;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {

        return product;
    }
}
