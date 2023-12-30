package com.OrderManagmentSystem.Models;

public class SmsChannel extends AbstractNotificationChannel {
    @Override
    public String createShippeedContent() {
        this.setContent("dear "+ order.getCustomerId()+" your booking of the order : "+ order.getProductsNames()+" is confirmed. thanks for using our store :)");
        return this.content;
    }

    @Override
    public String createPlacedContent() {
        this.setContent("dear "+ order.getCustomerId()+" your "+ order.getOrderId()+" has arrived to your address sucessfully !");
        return this.content;
    }
}