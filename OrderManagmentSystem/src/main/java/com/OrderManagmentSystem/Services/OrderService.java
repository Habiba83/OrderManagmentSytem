package com.OrderManagmentSystem.Services;
import com.OrderManagmentSystem.Models.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {


    private final List<Order> orders;

    public OrderService() {
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

    public boolean deleteOrder(String orderId) {
        return orders.removeIf(order -> order.getOrderId().equals(orderId));
    }

    public Order updateOrder(String orderId , Order updatedOrder) {
        orders.removeIf(order -> order.getOrderId().equals(orderId));
        orders.add(updatedOrder);
        return updatedOrder;
    }

    public Order shipOrder(String orderId) {
        System.out.println("shipping the order...");
        System.out.println("deducting shipping fees...");

        return getOrder(orderId);

    }
}
