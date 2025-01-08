package com.scaler.capstone.repos;

import com.scaler.capstone.models.Category;
import com.scaler.capstone.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    @Transactional // due to test,it  automatically rollback does
    void testFetchTypes(){
        Category category = categoryRepo.findById(1L).get();
//        category.getProducts().forEach(System.out::println); // fetch will happen LAZY & will not load from DB
        for(Product product : category.getProducts()){ // fetch will happen and will load from DB
            System.out.println(product.getName());
        }

        //Note: When EAGER -> it will always fetch product
    }

    @Test
    @Transactional
    void testSomething(){
        List<Category> catList = categoryRepo.findAll();
        for(Category category : catList){
            for(Product product : category.getProducts()){
                System.out.println(product.getName());
            }
        }
    }
}