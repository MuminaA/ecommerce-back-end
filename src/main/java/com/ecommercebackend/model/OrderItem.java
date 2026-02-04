package com.ecommercebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relationship to Order (Many OrderItems belong to One Order)
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)  // Creates FK column "order_id"
    @JsonIgnoreProperties("orderItems") // Prevents circular reference
    private Order order;

    // Relationship to Product (Many OrderItems reference One Product)
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)  // Creates FK column "product_id"
    @JsonIgnoreProperties({"description", "stock"})  // Only include essential product info
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal priceAtPurchase;

    // Transient field to accept productId from JSON (not persisted to DB)
    @Transient
    private Long productId;

    public OrderItem() {}

    public OrderItem(Long id, Order order, Product product, Integer quantity, BigDecimal priceAtPurchase) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(BigDecimal priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }

    // Getter and setter for productId
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
