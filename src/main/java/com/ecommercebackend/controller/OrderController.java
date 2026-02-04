package com.ecommercebackend.controller;

import com.ecommercebackend.model.Order;
import com.ecommercebackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
//@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // ============ CUSTOMER ENDPOINTS ============

    // Create new order (customer checkout)
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        System.out.println("=====================================");
        System.out.println("=== CREATE ORDER CALLED ===");
        System.out.println("Customer: " + order.getCustomerName());
        System.out.println("Email: " + order.getCustomerEmail());
        System.out.println("Items: " + (order.getOrderItems() != null ? order.getOrderItems() : "NULL"));

        try {
            if (order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
                System.out.println("❌ ERROR: Order has no items");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            Order createdOrder = orderService.createOrder(order);
            System.out.println("✅ SUCCESS! Order created: " + createdOrder.getId());
            System.out.println("=====================================");
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
        } catch (Exception e) {
            System.out.println("❌ ERROR:");
            e.printStackTrace();
            System.out.println("=====================================");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Get single order by ID (for confirmation page or tracking)
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ============ ADMIN ENDPOINTS ============

    // Get all orders (admin dashboard)
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // Update order status (admin marks as shipped/completed)
    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {

        String status = request.get("status");

        try {
            Order updatedOrder = orderService.updateOrderStatus(id, status);
            return ResponseEntity.ok(updatedOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
