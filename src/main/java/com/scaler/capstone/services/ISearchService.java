package com.scaler.capstone.services;

import com.scaler.capstone.dtos.SortParam;
import com.scaler.capstone.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISearchService {
    Page<Product> searchProducts(String searchQuery, int pageSize, int pageNumber, List<SortParam> sortParams);
}
