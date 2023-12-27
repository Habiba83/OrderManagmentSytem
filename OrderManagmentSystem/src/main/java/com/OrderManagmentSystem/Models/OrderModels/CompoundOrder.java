package com.OrderManagmentSystem.Models.OrderModels;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CompoundOrder extends Order {
    @JsonProperty("subOrders")
    private  List<SimpleOrder> subOrders;
    public CompoundOrder() {
    }

    @Override
    public String getOrderId() {
        return orderId;
    }


    public void setSubOrders(List<SimpleOrder> subOrders) {
        this.subOrders = subOrders;
    }


    private String getMyDetails (){
        return "CompoundOrder " +
                "orderId='" + orderId + '\'' +
                ", products=" + products +
                ", customerId='" + customerId + '\'' +
                ", shippingFees=" + shippingFees +
                ", isShipped=" + isShipped +
                ", SUB_ORDERS : " ;
    }


    @Override
    public String orderDetails(){
        StringBuilder allDetails = new StringBuilder();
        allDetails.append(getMyDetails());
        for (Order order: subOrders){
            allDetails.append(order.orderDetails());
            allDetails.append("\n");
        }
        return String.valueOf(allDetails);
    }

    @Override
    public List<SimpleOrder> getSubOrders() {
        return subOrders;
    }

}
