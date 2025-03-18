package com.scaler.capstone.services;

import com.scaler.capstone.clients.FakeStoreApiClient;
import com.scaler.capstone.dtos.FakeStoreProductDto;
import com.scaler.capstone.dtos.ProductDto;
import com.scaler.capstone.models.Category;
import com.scaler.capstone.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fps")
@Primary
public class FakeStoreProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    // instead use @Autowired
//    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplateBuilder = restTemplateBuilder;
//    }

    @Autowired
    private RedisTemplate<String, Product> redisTemplate;

    @Autowired
    FakeStoreApiClient fakeStoreApiClient;

    @Override
    public Product getProductById(Long productId) {
        // If found in Redis
        // return from redis
        // else fakeStore
        // cache it
        // return it
        FakeStoreProductDto fakeStoreProductDto = null;
        fakeStoreProductDto = (FakeStoreProductDto) redisTemplate.opsForHash().get("product", productId);
        if (fakeStoreProductDto == null) {
            System.out.println("No product found with id " + productId);
            fakeStoreProductDto = fromProduct(fakeStoreApiClient.getProductById(productId));
            redisTemplate.opsForHash().put("product", productId, fakeStoreProductDto);
        }else {
            System.out.println("Product found with id " + productId);
        }
        return toProduct(fakeStoreProductDto);
    }

    public FakeStoreProductDto fromProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setImage(product.getImageUrl());

        return fakeStoreProductDto;
    }

    public Product toProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        Category cat =  new Category();
        cat.setName(fakeStoreProductDto.getCategory());
        product.setCategory(cat);
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());

        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return fakeStoreApiClient.getAllProducts();
    }

//    @Override
//    public List<Product> getAllProducts() {
//        List<Product> products = new ArrayList<>();
//        RestTemplate restTemplate = restTemplateBuilder.build();
//
//        ResponseEntity<FakeStoreProductDto[]> listResponseEntity =
//                restTemplate.getForEntity("http://fakestoreapi.com/products",
//                        FakeStoreProductDto[].class);
//
//        for(FakeStoreProductDto fakeStoreProductDto : listResponseEntity.getBody()) {
//            products.add(from(fakeStoreProductDto));
//        }
//
//        return products;
//    }


    @Override
    public Product replaceProduct(Long productId, Product request) {
        return fakeStoreApiClient.replaceProduct(productId, request);
    }

    @Override
    public Product saveProduct(Product request) {
        return fakeStoreApiClient.saveProduct(request);
    }

    @Override
    public Product getProductBasedOnUserId(Long pid, Long uid) {
        return null;
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
