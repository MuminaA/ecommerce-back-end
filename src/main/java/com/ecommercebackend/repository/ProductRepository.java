package com.ecommercebackend.repository;

import com.ecommercebackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // JpaRepository gives you these methods automatically:
    // - save()
    // - findAll()
    // - findById()
    // - deleteById()
    // - count()

    // Order by ID ascending
    List<Product> findAllByOrderByIdAsc();

    // Custom query methods (optional for now):
    List<Product> findByCategory(String category);
    // List<Product> findByNameContaining(String name);
}
