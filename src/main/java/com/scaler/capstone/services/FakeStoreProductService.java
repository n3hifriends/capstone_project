package com.scaler.capstone.services;

import com.scaler.capstone.dtos.FakeStoreProductDto;
import com.scaler.capstone.models.Category;
import com.scaler.capstone.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FakeStoreProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    // instead use @Autowired
//    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplateBuilder = restTemplateBuilder;
//    }

    @Override
    public Product getProductById(Long productId) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplateBuilder.build().getForEntity("https://fakestoreapi.com/products/{productId}", FakeStoreProductDto.class, productId);
        if (fakeStoreProductDtoResponseEntity.getStatusCode().is2xxSuccessful() &&
                fakeStoreProductDtoResponseEntity.getBody() != null) {
            return createProduct(fakeStoreProductDtoResponseEntity.getBody());
        }
        return null;

    }

    private Product createProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setCategory(new Category());
        product.setImageUrl(fakeStoreProductDto.getImage());
        return product;
    }

}
