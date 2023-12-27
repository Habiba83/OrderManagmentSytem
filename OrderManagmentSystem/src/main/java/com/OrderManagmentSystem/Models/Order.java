package com.OrderManagmentSystem.Models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

//@JsonTypeInfo(
//        use = JsonTypeInfo.Id.NAME,
//        include = JsonTypeInfo.As.PROPERTY,
//        property = "orderType" // This property will indicate the concrete type
//)
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = SimpleOrder.class, name = "simpleOrder"),
//        @JsonSubTypes.Type(value = CompoundOrder.class, name = "compoundOrder")
//})

@Data
public abstract class Order {

    protected String orderId;
    protected List<Product> products;
    protected Customer customer;

    public double getShippingFees() {
        return shippingFees;
    }

    public boolean isShipped() {
        return isShipped;
    }

    public void setShipped(boolean shipped) {
        isShipped = shipped;
    }

    protected double shippingFees;
    protected boolean isShipped;

    public Order() {
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

    public void setShippingFees(double shippingFees) {
        this.shippingFees = shippingFees;
    }

    public abstract void shipOrder();
    public abstract void unshipOrder();
    public abstract String orderDetails();
    public abstract double getAllCost();


}
