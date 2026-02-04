package com.ecommercebackend.service;

import com.ecommercebackend.model.Order;
import com.ecommercebackend.model.OrderItem;
import com.ecommercebackend.model.Product;
import com.ecommercebackend.repository.OrderRepository;
import com.ecommercebackend.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    // Get all orders (for admin)
    public List<Order> getAllOrders() {
        return orderRepository.findAllByOrderByOrderDateDesc();  // Newest first
    }

    // Get single order by ID
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // Create new order (when customer checks out)
    @Transactional  // ← Important! Ensures all-or-nothing save
    public Order createOrder(Order order) {
        // Calculate total from order items
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : order.getOrderItems()) {
            // Get productId from transient field or from product object
            Long productId = item.getProductId();

            if (productId == null && item.getProduct() != null) {
                productId = item.getProduct().getId();
            }

            if (productId == null) {
                throw new RuntimeException("Product ID is required for order item");
            }

            final Long finalProductId = productId;

            // Fetch product to get current price
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + finalProductId));

            // Set the product reference on the item
            item.setProduct(product);

            // Set price at purchase (in case price changes later)
            item.setPriceAtPurchase(product.getPrice());

            // Calculate line total
            BigDecimal lineTotal = product.getPrice()
                    .multiply(new BigDecimal(item.getQuantity()));
            total = total.add(lineTotal);

            // Link item to order
            item.setOrder(order);
        }

        order.setTotalAmount(total);
        order.setStatus("pending");  // Default status

        return orderRepository.save(order);  // Cascade saves order items too!
    }

    // Update order status (for admin)
    public Order updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        order.setStatus(status);
        return orderRepository.save(order);
    }
}
