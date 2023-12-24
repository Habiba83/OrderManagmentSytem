package com.OrderManagmentSystem.Models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public  class Order {

    private String orderId;
    private List<Product> products;
    private Customer customer;
    private final List<Order> subOrders; // For compound orders

    public Order() {
        this.subOrders = new ArrayList<>();
    }

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addSubOrder(Order subOrder) {
        subOrders.add(subOrder);
    }

    public List<Order> getSubOrders (){
        return subOrders;
    }
}
