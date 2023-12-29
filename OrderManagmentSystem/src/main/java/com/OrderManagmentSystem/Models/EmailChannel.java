package com.OrderManagmentSystem.Models;

import org.springframework.stereotype.Component;

@Component
public class EmailChannel extends AbstractNotificationChannel {



    @Override
    public String createPlacedContent() {
        this.setContent("dear "+ order.getCustomerId()+" your booking of the "+ order.getOrderId()+" is confirmed. thanks for using our store :)");
        return this.content;
    }

    @Override
    public String createShippeedContent() {
        this.setContent("dear "+ order.getCustomerId()+" your "+ order.getOrderId()+" has arrived to your address sucessfully !");
        return this.content;
    }
}
