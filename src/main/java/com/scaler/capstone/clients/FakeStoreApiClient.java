package com.scaler.capstone.clients;

import com.scaler.capstone.dtos.FakeStoreProductDto;
import com.scaler.capstone.models.Category;
import com.scaler.capstone.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component //
public class FakeStoreApiClient {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public <T> ResponseEntity<T> postForEntity(String url, HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    public Product getProductById(Long productId) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = postForEntity("https://fakestoreapi.com/products/{productId}", HttpMethod.GET, null, FakeStoreProductDto.class, productId);
            return validate(createProduct(fakeStoreProductDtoResponseEntity.getBody()));
    }

    public List<Product> getAllProducts() {
        ResponseEntity<FakeStoreProductDto[]> responseEntity = postForEntity("https://fakestoreapi.com/products",HttpMethod.GET, null, FakeStoreProductDto[].class);

        List<Product> products = Arrays.stream(responseEntity.getBody())
                .map(this::createProduct) // Use createProduct to map each DTO to a Product
                .collect(Collectors.toList());

        return products;
    }

    public Product replaceProduct(Long productId, Product request) {
        ResponseEntity<Product> responseEntity = postForEntity("https://fakestoreapi.com/products/{productId}", HttpMethod.PUT, request, Product.class, productId);
        return responseEntity.getBody();
    }

    public Product saveProduct(Product request) {
        ResponseEntity<Product> responseEntity = postForEntity("https://fakestoreapi.com/products", HttpMethod.POST, request, Product.class);
        return responseEntity.getBody();
    }

    private Product validate(Product product){
        if(product == null){
            throw new IllegalArgumentException("Oops, something went wrong");
        }
        return product;
    }

    private Product createProduct(FakeStoreProductDto fakeStoreProductDto) {
        if(fakeStoreProductDto == null){
            throw new IllegalArgumentException("Oops, something went wrong");
        }
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
