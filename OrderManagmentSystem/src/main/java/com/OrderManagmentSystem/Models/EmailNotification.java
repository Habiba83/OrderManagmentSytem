package com.OrderManagmentSystem.Models;

import com.OrderManagmentSystem.Services.NotificationService;
import org.springframework.stereotype.Component;

import java.util.List;
public class EmailNotification implements NotificationObserver {
    private Template template;
    private String content;
    private Customer customer;
    private List<String> languages;
    NotificationService notificationService;

    public EmailNotification(Template template, Customer customer, NotificationService notificationService) {
        this.template = template;
        this.customer = customer;
        this.notificationService = notificationService;
    }

    public EmailNotification(Template template, Customer customer) {
        this.template = template;
        this.customer = customer;
    }

    public Template getTemplate() {
        return template;
    }

    public String getContent() {
        return content;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void update() {
        notificationService.createEmailNotification(this);
    }
}
