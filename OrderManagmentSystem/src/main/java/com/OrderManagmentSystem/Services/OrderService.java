package com.OrderManagmentSystem.Services;

import com.OrderManagmentSystem.Models.Order;
import com.OrderManagmentSystem.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        // Implement order creation logic
        return orderRepository.addOrder(order);
    }

    public Order getOrder(String orderId) {
        return orderRepository.getOrder(orderId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.getAll();
    }
}
