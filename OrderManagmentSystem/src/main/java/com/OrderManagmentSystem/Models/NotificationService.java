package com.OrderManagmentSystem.Models;

import com.OrderManagmentSystem.Models.EmailNotification;
import com.OrderManagmentSystem.Models.NotificationObserver;
import com.OrderManagmentSystem.Models.Order;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    NotificationObserver notificationObserver;

    public void setNotificationObserver(NotificationObserver notificationObserver) {
        this.notificationObserver = notificationObserver;
    }
    public String createEmailNotification(EmailNotification emailNotification){
        switch (emailNotification.getTemplate()){
            case placementOrder :
                emailNotification.setContent("dear "+ emailNotification.getCustomer().getAddress()+" ,your booking for "+ emailNotification.getCustomer().getOrder().getOrderId()+ " is confirmed. thanks for using our store :) ");
            break;
            case ShippedOrder:
                emailNotification.setContent("dear "+ emailNotification.getCustomer().getEmail() + " ,your order "+ emailNotification.getCustomer().getOrder().getOrderId()+" has been arrived at "+ emailNotification.getCustomer().getAddress()+"successfully");
                break;
        }
        return emailNotification.getContent();
    }
}
