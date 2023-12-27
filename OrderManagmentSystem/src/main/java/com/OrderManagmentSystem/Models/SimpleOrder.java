package com.OrderManagmentSystem.Models;

public class SimpleOrder extends Order{
//    private final String orderType = "simpleOrder";

    private double calculateCost(){
        double cost = 0.0;

        for (Product product : products){
            cost += (product.getCount() * product.getPrice());
        }
        return cost;
    }

    @Override
    public void shipOrder() {
        if (!isShipped) {
            if (customer != null) {
                customer.deductBalance(shippingFees);
            }
            isShipped = true;
        }
    }

    public SimpleOrder() {
    }

    @Override
    public void unshipOrder() {
        if (isShipped) {
            if (customer != null) {
                customer.addBalance(shippingFees);
            }
            isShipped = false;
        }
    }

    @Override
    public String orderDetails(){

        return "SimpleOrder{" +
                "orderId='" + orderId + '\'' +
                ", products=" + products +
                ", customer=" + customer +
                ", shippingFees=" + shippingFees +
                ", isShipped=" + isShipped +
                '}';
    }

    @Override
    public double getAllCost() {
        return calculateCost() + shippingFees;
    }


}
