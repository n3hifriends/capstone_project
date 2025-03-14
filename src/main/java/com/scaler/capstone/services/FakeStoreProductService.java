package com.scaler.capstone.services;

import com.scaler.capstone.clients.FakeStoreApiClient;
import com.scaler.capstone.dtos.FakeStoreProductDto;
import com.scaler.capstone.dtos.ProductDto;
import com.scaler.capstone.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fps")
@Primary
public class FakeStoreProductService implements IProductService {



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

        return fakeStoreProductDto;
    }

    public Product toProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return fakeStoreApiClient.getAllProducts();
    }

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
}
