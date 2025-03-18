package com.scaler.capstone.repos;

import com.scaler.capstone.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepoTest {

    @Autowired
    public ProductRepo productRepo;

    // for inserting data into DB purpose while working on aws
    @Test
    public void insertProductInDatabase() {
        Product product = new Product();
        product.setName("test");
        product.setPrice((double) 34L);
        product.setId(3243L);
        productRepo.save(product);

        Product product1 = new Product();
        product1.setId(3423L);
        product1.setName("test1");
        product1.setPrice((double) 341L);
        productRepo.save(product1);
    }

    @Test
    public void testProductByOrderJpa(){
        List<Product> products = productRepo.findProductByOrderByPrice();
        products.forEach(System.out::println);
    }

    @Test
    public void testProductTitleJpa(){
        String title = productRepo.findProductTitleById(3423L);
        System.out.println(title);
    }

    @Test
    public void testJpa(){
        String cateName = productRepo.findCategoryNameFromProductId(3423L);
        System.out.println(cateName);
    }
}
