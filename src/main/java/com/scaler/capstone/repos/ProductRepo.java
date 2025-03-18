package com.scaler.capstone.repos;

import com.scaler.capstone.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // to create Bean
public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findProductById(Long id);
    Product save(Product product);
    List<Product> findAll();

    List<Product> findProductByOrderByPrice();

    @Query("SELECT p.name FROM Product p WHERE p.id = ?1")
    String findProductTitleById(Long id);

    @Query("SELECT c.name from Category c join Product p on p.category.id = c.id where p.id=:pid")
    String findCategoryNameFromProductId(Long pid);

    Page<Product> findProductByName(String name, Pageable pageable);
}
