package com.OrderManagmentSystem.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CompoundOrder extends Order{
    @JsonProperty("subOrders")
    private  List<SimpleOrder> subOrders;
//    private final String orderType = "compoundOrder";

    public CompoundOrder() {
    }

    public void setSubOrders(List<SimpleOrder> subOrders) {
        this.subOrders = subOrders;
    }

    public List<SimpleOrder> getSubOrders() {
        return subOrders;
    }

    private double getMyCost (){
        double cost = 0.0;
        for (Product product : products){
            cost += (product.getCount() * product.getPrice());
        }
        return cost+shippingFees;
    }
    private String getMyDetails (){

        return "CompoundOrder " +
                "orderId='" + orderId + '\'' +
                ", products=" + products +
                ", customer=" + customer +
                ", shippingFees=" + shippingFees +
                ", isShipped=" + isShipped +
                ", SUB_ORDERS : " ;
    }




    @Override
    public void shipOrder() {
        this.customer.deductBalance(shippingFees);
        for (Order order: subOrders){
            order.shipOrder();
        }
    }

    @Override
    public void unshipOrder() {
        this.customer.addBalance(shippingFees);
        for (Order order: subOrders){
            order.unshipOrder();
        }

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
    public double getAllCost() {
        double cost = 0.0;
        for (Order order : subOrders){
            cost += order.getAllCost();
        }
        return cost + getMyCost();
    }

}
