package com.scaler.capstone.repos;

import com.scaler.capstone.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

//@RunWith(SpringRunner.class)
@SpringBootTest()
public class ProductRepoTest {

    @Autowired
    public ProductRepo productRepo;

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
