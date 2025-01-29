package com.scaler.capstone.controllers;

import com.scaler.capstone.dtos.SearchRequestDto;
import com.scaler.capstone.models.Product;
import com.scaler.capstone.services.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // to Create a Bean
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @PostMapping
    public Page<Product> serachProducts(@RequestBody SearchRequestDto searchRequestDto) {
        return searchService.searchProducts(searchRequestDto.getSearchQuery(), searchRequestDto.getPageSize(),
                searchRequestDto.getPageNumber(), searchRequestDto.getSortParams());
    }

    // above input args +
    // sortParam: [{
    //  },
    //      {
    //          }]
}
