package com.scaler.capstone.repos;

import com.scaler.capstone.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // to create Bean
public interface ProductRepo extends JpaRepository<Product, Long> {

}
