package com.scaler.capstone.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) // whenever json object create get those are only non-null fields
public class ProductDto {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
}
