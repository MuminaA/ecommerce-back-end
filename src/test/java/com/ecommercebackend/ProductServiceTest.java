package com.ecommercebackend;

import com.ecommercebackend.model.Product;
import com.ecommercebackend.repository.ProductRepository;
import com.ecommercebackend.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProductById_shouldReturnProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(new BigDecimal("29.99"));

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getProductById(1L);

        assertTrue(result.isPresent());
        assertEquals("Test Product", result.get().getName());
        assertEquals(new BigDecimal("29.99"), result.get().getPrice());
    }

    @Test
    void getProductById_shouldReturnEmpty_whenNotFound() {
        when(productRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Product> result = productService.getProductById(999L);

        assertTrue(result.isEmpty());
    }

    @Test
    void createProduct_shouldSaveProduct() {
        Product product = new Product();
        product.setName("New Product");
        product.setPrice(new BigDecimal("49.99"));

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product result = productService.createProduct(product);

        assertNotNull(result);
        assertEquals("New Product", result.getName());
        verify(productRepository, times(1)).save(product);
    }
}
