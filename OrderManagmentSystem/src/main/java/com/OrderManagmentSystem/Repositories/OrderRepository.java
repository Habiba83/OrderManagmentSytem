package com.OrderManagmentSystem.Repositories;

import com.OrderManagmentSystem.Models.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class OrderRepository {

    private List<Order> orders;

    public OrderRepository() {
        this.orders = new ArrayList<>();
    }

    public Order addOrder(Order order){
        orders.add(order);
        return order;
    }

    public Order getOrder(String orderId) {

        for (Order order: orders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    public List<Order> getAll() {
        return orders;
    }
}
