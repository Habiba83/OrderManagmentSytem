package com.OrderManagmentSystem.Models;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    private String orderId;
    private List<Product> products;
    private List<Order> compoundOrders; // For compound orders
    private Customer customer;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Order> getCompoundOrders() {
        return compoundOrders;
    }

    public void setCompoundOrders(List<Order> compoundOrders) {
        this.compoundOrders = compoundOrders;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // getters and setters
}