package com.ecommercebackend.repository;

import com.ecommercebackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Get all orders, newest first
    List<Order> findAllByOrderByOrderDateDesc();

    // Get all orders, oldest first (or by ID)
    List<Order> findAllByOrderByIdAsc();
}
