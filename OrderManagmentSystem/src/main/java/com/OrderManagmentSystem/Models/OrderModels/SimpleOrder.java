package com.OrderManagmentSystem.Models.OrderModels;

import java.util.List;

public class SimpleOrder extends Order {


    public SimpleOrder() {
    }

    @Override
    public String getOrderId() {
        return orderId;
    }

    @Override
    public String orderDetails(){

        return "SimpleOrder{" +
                "orderId='" + orderId + '\'' +
                ", products=" + products +
                ", customerId=" + customerId +
                ", shippingFees=" + shippingFees +
                ", isShipped=" + isShipped +
                '}';
    }

    @Override
    public List<SimpleOrder> getSubOrders() {
        return null;
    }



}
