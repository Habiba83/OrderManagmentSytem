package com.OrderManagmentSystem.Services;
import com.OrderManagmentSystem.Models.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {


    private final List<Order> orders;
    private final List<Order> shippingOrders;

    public OrderService(List<Order> shippingOrders) {
        this.shippingOrders = new ArrayList<>();
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
    public List<Order> getAllShippingOrders() {
        return shippingOrders;
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
        Order order = getOrder(orderId);
        System.out.println(order);
        order.shipOrder();
        shippingOrders.add(order);
        System.out.println("shipping the order..." + orderId);
        return order;

    }

    public String cancelShipment(String orderId) {
        Order order1 = getOrder(orderId);
        order1.unshipOrder();
        shippingOrders.removeIf(order -> order.getOrderId().equals(orderId));

        return "cancel shipping the order..."+ orderId;

    }
}
