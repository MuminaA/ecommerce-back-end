package com.ecommercebackend.repository;

import com.ecommercebackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // JpaRepository gives you these methods automatically:
    // - save()
    // - findAll()
    // - findById()
    // - deleteById()
    // - count()

    // Order by ID ascending
    @Query("SELECT p FROM Product p WHERE p.isDeleted = false ORDER BY p.id ASC")
    List<Product> findAllByOrderByIdAsc();

    @Query("SELECT p FROM Product p WHERE p.id = ?1 AND p.isDeleted = false")
    Optional<Product> findById(Long id);

    // Custom query methods (optional for now):
    //    List<Product> findByCategory(String category);
    // List<Product> findByNameContaining(String name);

    List<Product> findByIsDeletedFalse();
    Optional<Product> findByIdAndIsDeletedFalse(Long id);
}
