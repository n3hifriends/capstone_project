package com.scaler.capstone.services;

import com.scaler.capstone.models.Product;
import com.scaler.capstone.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("sps")
//@Primary // If IProductService implements multiple classes, it will consider this class
public class StorageProductService implements IProductService{

    @Autowired
    private ProductRepo productRepo;

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
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product replaceProduct(Long productId, Product request) {
        return null;
    }
}
