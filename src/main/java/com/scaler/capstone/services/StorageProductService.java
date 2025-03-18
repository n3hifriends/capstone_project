package com.scaler.capstone.services;

import com.scaler.capstone.dtos.ProductDto;
import com.scaler.capstone.models.Product;
import com.scaler.capstone.models.UserDto;
import com.scaler.capstone.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service("sps")
//@Primary // If IProductService implements multiple classes, it will consider this class
public class StorageProductService implements IProductService{

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Product getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepo.findProductById(productId);
        if(optionalProduct.isEmpty()){
            return null;
        }
        return optionalProduct.get();
    }

    @Override
    public Product saveProduct(Product request) {
        Product product =  productRepo.save(request);
        if(product == null){
            return null;
        }
        return product;
    }

    @Override
    public Product getProductBasedOnUserId(Long pid, Long uid) {
        Optional<Product> productOptional = productRepo.findProductById(pid);
//        RestTemplate restTemplate = new RestTemplate();
        UserDto userDto = restTemplate.getForEntity("http://UserAuthenticationServices/users/userId/{uid}" , UserDto.class, uid).getBody();
        if(userDto != null) {
            return productOptional.orElse(null);
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product replaceProduct(Long productId, Product request) {
        return null;
    }
}
