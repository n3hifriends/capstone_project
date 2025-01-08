package com.scaler.capstone.controllers;

import com.scaler.capstone.dtos.ProductDto;
import com.scaler.capstone.models.Product;
import com.scaler.capstone.services.IProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private IProductService productService;

    @Test
    @DisplayName("get product with Id 4 will run fine")
    public void Test_GetProductById_ReturnsProductSuccessfully() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("test");
        Mockito.when(productService.getProductById(productId)).thenReturn(product);

        //Act
        ResponseEntity<ProductDto> resProductById = productController.findProductById(productId);

        assertNotNull(resProductById);
        assertNotNull(resProductById.getBody());
        assertEquals(productId, resProductById.getBody().getId());
        assertEquals("test", resProductById.getBody().getTitle());
        Mockito.verify(productService,Mockito.times(1)).getProductById(productId);
    }

    @Test
    public void Test_GetProductById_CalledWithInvalidId_ResultsInIllegalArgumentException(){
        // Arrange
        // not needed


        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {productController.findProductById(-1L); });

        assertEquals("Invalid product id", exception.getMessage());
        Mockito.verify(productService,Mockito.times(0)).getProductById(Mockito.anyLong());
    }
}