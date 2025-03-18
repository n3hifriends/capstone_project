package com.scaler.capstone.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String name;
    private String description;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)// to avoid circular dependency with Product class
    @Fetch(FetchMode.SELECT)
    private List<Product> products;

    // try with
    // LAZY, EAGER
    // SELECT, JOIN, SUB SELECT
    // getProduct or doNot call getProduct


    // Execute query in batches
    // @Fetch(FetchMode.SELECT)
    // @BatchSize(size = 2)
    // private List<Product> products;

}
