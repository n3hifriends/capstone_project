package com.scaler.capstone.controllers;

import com.scaler.capstone.dtos.ProductDto;
import com.scaler.capstone.models.Category;
import com.scaler.capstone.models.Product;
import com.scaler.capstone.models.State;
import com.scaler.capstone.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    @Qualifier("fps")
    private IProductService productServiceFps;

    @Autowired
    @Qualifier("sps")
    private IProductService productServiceSps;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        List<Product> products = productServiceFps.getAllProducts();
        return products.stream().map(this::toProductDto).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable Long id) {
        try {

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            if (id < 0) {
                throw new IllegalArgumentException("Invalid product id"); // thrown from ControllerAdvice, it will call automatically
            } else if (id == 0) {
                throw new IllegalArgumentException("Product id can't be 0"); // thrown from ControllerAdvice, it will call automatically
            }
            Product product = productServiceFps.getProductById(id);
            if (product == null) {
                throw new IllegalArgumentException("Invalid product id"); // thrown from ControllerAdvice, it will call automatically
            }
            params.add("called by", "Intelligent");
            return new ResponseEntity<>(toProductDto(product), params, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid product id"); // thrown from ControllerAdvice, it will call automatically
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error", e);
        }
    }

    public ProductDto toProductDto(Product product) {
        if (product != null) {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setTitle(product.getName());
            productDto.setPrice(product.getPrice());
            productDto.setDescription(product.getDescription());
            productDto.setCategory(product.getCategory().getName());
            productDto.setImage(product.getImageUrl());
            return productDto;
        }
        return null;
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductDto request) {
        return productServiceFps.saveProduct(_createProduct(request));
    }

    @PutMapping("/{id}")
    public ProductDto replaceProduct(@PathVariable Long id, @RequestBody ProductDto request) {
        Product product = productServiceFps.replaceProduct(id, _createProduct(request));
        return toProductDto(product);
    }

    private Product _createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());

        Category cat = new Category();
        Random random = new Random();
        long randomId = random.nextLong();
        // Ensure the random value is positive
        if (randomId < 0) {
            randomId = -randomId;
        }
        cat.setId(randomId);
        cat.setCreatedAt(new Date());
        cat.setName(productDto.getCategory());
        cat.setDescription(productDto.getCategory());
        cat.setLastUpdatedAt(new Date());
        cat.setState(State.ACTIVE);
        product.setCategory(cat);

        product.setImageUrl(productDto.getImage());
        return product;
    }

    @GetMapping("{pid}/{uid}")
    public ResponseEntity<ProductDto> getProductBasedOnUserId(@PathVariable Long pid, @PathVariable Long uid) {
        Product productBasedOnUserId = productServiceFps.getProductBasedOnUserId(pid, uid);
        ProductDto productDto = toProductDto(productBasedOnUserId);
        if(productDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        }
    }

}
