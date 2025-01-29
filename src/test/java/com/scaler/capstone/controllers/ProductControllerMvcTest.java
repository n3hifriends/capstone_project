package com.scaler.capstone.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaler.capstone.dtos.ProductDto;
import com.scaler.capstone.models.Product;
import com.scaler.capstone.services.IProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTest {

    @MockBean
    @Qualifier("fps")
    private IProductService productService;

    //MockMvc -> simulates as exact response like postman or dummy like controller call

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Captor
    private ArgumentCaptor<Long> productIdCaptor; // input params to service

    @Test
    @DisplayName("MockMvc_Test")
    public void Test_GetAllProducts_RunsSuccessfully() throws Exception {
        //Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("Test Description");


        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Test Product2");
        product2.setDescription("Test Description2");

        List<Product> products = Arrays.asList(product, product2);

        OngoingStubbing<List<Product>> listOngoingStubbing = Mockito.when(productService.getAllProducts())
                .thenReturn(products);


        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(products)))
                // or we can use below lines
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(product.getId()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].length()").value(3)); // .length() give no. of attributes
    }

    @Test
    public void Test_CreateProduct_RunsSuccessfully() throws Exception {

        //Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("Test Description");


        Mockito.when(productService.saveProduct(Mockito.any(Product.class))).thenReturn(product);

        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setDescription(product.getDescription());
        productDto.setTitle(product.getName());


        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .content(objectMapper.writeValueAsString(productDto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(productDto)))
                // or we can do like below instead of above line
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(product.getId()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(product.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(product.getName()));
    }

    @Test
    public void Test_GetProductById_ProductServiceCAlledWithCorrectArguments_RunsSuccessfully() throws Exception {
        //Arrange
        Long productId = 1L;

        Product product = new Product();
        product.setId(productId);
        product.setName("Test Product");
        product.setDescription("Test Description");

        Mockito.when(productService.getProductById(productId)).thenReturn(product);


        Mockito.verify(productService.getProductById(productIdCaptor.capture()));
        Assertions.assertEquals(productId, productIdCaptor.getValue());
    }
}
