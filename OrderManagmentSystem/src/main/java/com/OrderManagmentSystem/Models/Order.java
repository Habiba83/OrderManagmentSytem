package com.OrderManagmentSystem.Models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public  class Order {

    private String orderId;
    private List<Product> products;
    private Customer customer;
    private double shippingFees;
    private boolean isShipped;
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

    public void setShippingFees(double shippingFees) {
        this.shippingFees = shippingFees;
    }

    public void shipOrder() {
        if (!isShipped) {
            if (isCompoundOrder()) {
                deductShippingFeesFromCustomers();
            } else {
                deductShippingFeesFromCustomer(customer);
            }
            isShipped = true;
        }
    }

    public void unshipOrder() {
        if (isShipped) {
            if (isCompoundOrder()) {
                refundShippingFeesToCustomers();
            } else {
                refundShippingFeesToCustomer(customer);
            }
            isShipped = false;
        }
    }

    private boolean isCompoundOrder() {
        return !subOrders.isEmpty();
    }

    private void deductShippingFeesFromCustomers() {
        deductShippingFeesFromCustomer(customer);

        for (Order subOrder : subOrders) {
            deductShippingFeesFromCustomer(subOrder.getCustomer());
        }
    }

    private void deductShippingFeesFromCustomer(Customer customer) {
        if (customer != null) {
            customer.deductBalance(shippingFees);
        }
    }

    private void refundShippingFeesToCustomers() {
        refundShippingFeesToCustomer(customer);

        for (Order subOrder : subOrders) {
            refundShippingFeesToCustomer(subOrder.getCustomer());
        }
    }

    private void refundShippingFeesToCustomer(Customer customer) {
        if (customer != null) {
            customer.addBalance(shippingFees);
        }
    }
}
