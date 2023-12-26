package com.OrderManagmentSystem.Models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class Order {

    protected String orderId;
    protected List<Product> products;
    protected Customer customer;
    protected double shippingFees;
    protected boolean isShipped;


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

    public void setShippingFees(double shippingFees) {
        this.shippingFees = shippingFees;
    }

    public abstract void shipOrder();
    public abstract void unshipOrder();
    public abstract String orderDetails();
    public abstract double getAllCost();



}
