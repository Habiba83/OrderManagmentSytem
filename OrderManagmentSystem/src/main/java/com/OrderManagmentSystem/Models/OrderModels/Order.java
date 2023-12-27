package com.OrderManagmentSystem.Models.OrderModels;


import lombok.Data;

import java.util.List;


@Data
public abstract class Order {
    protected String orderId;
    protected List<ProductReq> products;
    protected String customerId;
    protected double shippingFees;
    protected boolean isShipped;

    public Order() {
    }


    public List<ProductReq> getProducts() {
        return products;
    }

    public void setProducts(List<ProductReq> products) {
        this.products = products;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    public double getShippingFees() {
        return shippingFees;
    }

    public boolean isShipped() {
        return isShipped;
    }

    public void setShipped(boolean shipped) {
        isShipped = shipped;
    }


    public abstract String getOrderId();

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setShippingFees(double shippingFees) {
        this.shippingFees = shippingFees;
    }

    public abstract String orderDetails();
    public abstract List<SimpleOrder> getSubOrders();



}
