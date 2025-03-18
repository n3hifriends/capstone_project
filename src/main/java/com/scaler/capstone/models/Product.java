package com.scaler.capstone.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL) // whenever json object create get those are only non-null fields
public class Product extends BaseModel {
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    @ManyToOne(cascade = CascadeType.ALL) // if all product delete related category should delete
    private Category category;
    private Boolean isPrime; // extra field
}
