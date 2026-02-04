package com.ecommercebackend.service;

import com.ecommercebackend.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
}
