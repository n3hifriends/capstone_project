package com.scaler.capstone.services;

import com.scaler.capstone.models.Product;

public interface IProductService {
    Product getProductById(Long productId);
}
