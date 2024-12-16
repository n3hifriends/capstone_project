package com.scaler.capstone.services;

import com.scaler.capstone.clients.FakeStoreApiClient;
import com.scaler.capstone.dtos.FakeStoreProductDto;
import com.scaler.capstone.models.Category;
import com.scaler.capstone.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
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


}
