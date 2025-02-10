package com.scaler.capstone.services;

import com.scaler.capstone.dtos.ProductDto;
import com.scaler.capstone.models.Product;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IProductService {
    Product getProductById(Long productId);
    List<Product> getAllProducts();
    Product replaceProduct(Long productId, Product request);
    Product saveProduct(Product request);
    Product getProductBasedOnUserId(Long pid, Long uid);
}
