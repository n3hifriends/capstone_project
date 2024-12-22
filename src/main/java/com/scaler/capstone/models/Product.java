package com.scaler.capstone.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    @ManyToOne(cascade = CascadeType.ALL) // if all product delete related category should delete
    private Category category;
    private Boolean isPrime; // extra field
}
