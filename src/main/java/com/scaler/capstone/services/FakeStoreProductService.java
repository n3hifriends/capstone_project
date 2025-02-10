package com.scaler.capstone.services;

import com.scaler.capstone.clients.FakeStoreApiClient;
import com.scaler.capstone.dtos.ProductDto;
import com.scaler.capstone.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fps")
public class FakeStoreProductService implements IProductService {



    // instead use @Autowired
//    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplateBuilder = restTemplateBuilder;
//    }

    @Autowired
    FakeStoreApiClient fakeStoreApiClient;

    @Override
    public Product getProductById(Long productId) {
        return fakeStoreApiClient.getProductById(productId);
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
