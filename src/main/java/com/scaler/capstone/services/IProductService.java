package com.scaler.capstone.services;

import com.scaler.capstone.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long productId);
    List<Product> getAllProducts();
    Product replaceProduct(Long productId, Product request);
    Product saveProduct(Product request);
}
