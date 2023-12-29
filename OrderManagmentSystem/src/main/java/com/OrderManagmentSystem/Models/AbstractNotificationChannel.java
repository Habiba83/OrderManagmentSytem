package com.OrderManagmentSystem.Models;

import com.OrderManagmentSystem.Models.OrderModels.Order;

public abstract class AbstractNotificationChannel implements NotificationChannel {
    protected Order order;
    protected String content;
    public AbstractNotificationChannel() {}



    public void setOrder(Order order) {
        this.order = order;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Order getOrder() {
        return order;
    }


    public String getContent() {
        return content;
    }

    public AbstractNotificationChannel( Order order) {
        this.order = order;
    }

    @Override
    public abstract String createPlacedContent();

    @Override
    public abstract String createShippeedContent();
}
