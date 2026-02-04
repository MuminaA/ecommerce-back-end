package com.ecommercebackend;

import com.ecommercebackend.controller.ProductController;
import com.ecommercebackend.model.Product;
import com.ecommercebackend.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts_shouldReturnListOfProducts() {
        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(2L);

        when(productService.getAllProducts()).thenReturn(Arrays.asList(product1, product2));

        ResponseEntity<List<Product>> response = productController.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void getProductById_shouldReturnProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        when(productService.getProductById(1L)).thenReturn(Optional.of(product));

        ResponseEntity<Product> response = productController.getProductById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Product", response.getBody().getName());
    }

    @Test
    void createProduct_shouldReturnCreatedProduct() {
        Product product = new Product();
        product.setName("New Product");

        when(productService.createProduct(any(Product.class))).thenReturn(product);

        ResponseEntity<Product> response = productController.createProduct(product);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("New Product", response.getBody().getName());
    }
}
