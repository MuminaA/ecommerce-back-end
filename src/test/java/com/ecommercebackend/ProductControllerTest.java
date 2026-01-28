//package com.ecommercebackend;
//
//import com.ecommercebackend.controller.ProductController;
//import com.ecommercebackend.model.Product;
//import com.ecommercebackend.service.ProductService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.math.BigDecimal;
//import java.util.Arrays;
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(ProductController.class)  // ← CRITICAL: This annotation is key!
//class ProductControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean  // ← This creates a mock of ProductService
//    private ProductService productService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    void shouldGetAllProducts() throws Exception {
//        // Given
//        Product product = new Product();
//        product.setId(1L);
//        product.setName("Test Product");
//        product.setPrice(new BigDecimal("49.99"));
//        product.setCategory("Canvas Print");
//        product.setStockQuantity(10);
//
//        when(productService.getAllProducts()).thenReturn(Arrays.asList(product));
//
//        // When/Then
//        mockMvc.perform(get("/api/products"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name").value("Test Product"))
//                .andExpect(jsonPath("$[0].price").value(49.99));
//    }
//
//    @Test
//    void shouldGetProductById() throws Exception {
//        // Given
//        Product product = new Product();
//        product.setId(1L);
//        product.setName("Test Product");
//        product.setPrice(new BigDecimal("49.99"));
//
//        when(productService.getProductById(1L)).thenReturn(Optional.of(product));
//
//        // When/Then
//        mockMvc.perform(get("/api/products/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("Test Product"));
//    }
//
//    @Test
//    void shouldCreateProduct() throws Exception {
//        // Given
//        Product product = new Product();
//        product.setId(1L);
//        product.setName("New Product");
//        product.setPrice(new BigDecimal("59.99"));
//        product.setCategory("Canvas Print");
//        product.setStockQuantity(10);
//
//        when(productService.createProduct(any(Product.class))).thenReturn(product);
//
//        // When/Then
//        mockMvc.perform(post("/api/products")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(product)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name").value("New Product"));
//    }
//
//    @Test
//    void shouldReturn404WhenProductNotFound() throws Exception {
//        // Given
//        when(productService.getProductById(999L)).thenReturn(Optional.empty());
//
//        // When/Then
//        mockMvc.perform(get("/api/products/999"))
//                .andExpect(status().isNotFound());
//    }
//}
