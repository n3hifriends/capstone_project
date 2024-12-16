package com.scaler.capstone.services;

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

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        List<Product> products = Arrays.stream(responseEntity.getBody())
                .map(this::createProduct) // Use createProduct to map each DTO to a Product
                .collect(Collectors.toList());

        return products;
    }

    @Override
    public Product replaceProduct(Long productId, Product request) {
        ResponseEntity<Product> responseEntity = postForEntity("https://fakestoreapi.com/products/{productId}", HttpMethod.PUT, request, Product.class, productId);
        return responseEntity.getBody();
    }


    public <T> ResponseEntity<T> postForEntity(String url, HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
}
