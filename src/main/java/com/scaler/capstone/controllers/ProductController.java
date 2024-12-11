package com.scaler.capstone.controllers;

import com.scaler.capstone.models.Category;
import com.scaler.capstone.models.Product;
import com.scaler.capstone.models.State;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

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
    public Product findProductById(@PathVariable Long id) {
        Product product = new Product();
        product.setId(id);

        return product;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {

        return product;
    }
}
