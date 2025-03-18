package com.scaler.capstone.services;

import com.scaler.capstone.dtos.SortParam;
import com.scaler.capstone.dtos.SortType;
import com.scaler.capstone.models.Product;
import com.scaler.capstone.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class JPASearchService implements ISearchService {

    @Autowired
    private ProductRepo productRepo;


    @Override
    public Page<Product> searchProducts(String searchQuery, int pageSize, int pageNumber, List<SortParam> sortParams) {
        AtomicReference<Sort> sort = new AtomicReference<>();
        if(sortParams.isEmpty()) {
            return productRepo.findProductByName(searchQuery, PageRequest.of(pageNumber, pageSize));
        }

        sortParams.forEach(sortParam -> {
            if(sortParam.getSortType().equals(SortType.ASC)) {
                sort.set(Sort.by(sortParam.getSortCriteria()));
            }else{
                sort.set(Sort.by(sortParam.getSortCriteria()).descending());
            }
        });

//        Sort sort = Sort.by(Sort.Direction.ASC, "price").and(Sort.by(Sort.Direction.DESC, "id"));
        return productRepo.findProductByName(searchQuery, PageRequest.of(pageNumber, pageSize, sort.get()));
    }
}
