package com.scaler.capstone.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class SearchRequestDto {
    int pageSize;
    int pageNumber;
    String searchQuery;
    List<SortParam> sortParams = new ArrayList<>();
}
