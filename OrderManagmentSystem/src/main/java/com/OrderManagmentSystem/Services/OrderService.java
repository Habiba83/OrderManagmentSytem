package com.OrderManagmentSystem.Services;
import com.OrderManagmentSystem.Models.*;
import com.OrderManagmentSystem.Models.OrderModels.SimpleOrder;
import com.OrderManagmentSystem.Models.OrderModels.Order;
import com.OrderManagmentSystem.Models.OrderModels.ProductReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.time.Duration;
import java.time.LocalDateTime;
@Service
@Scope("singleton")
public class OrderService {


    private final List<Order> orders;
    private final List<Order> shippingOrders;
    private final Queue<String> ShippedOrdernotificationContents;
    private final Queue<String> placedOrdernotificationsContent;

    @Autowired
    private final ProductService productService;
    @Autowired
    private final CustomerService customerService;

    public OrderService(ProductService productService, CustomerService customerService) {
        this.orders = new ArrayList<>();
        this.shippingOrders = new ArrayList<>();
        this.productService = productService;
        this.customerService = customerService;
        this.ShippedOrdernotificationContents=new LinkedList<>();
        this.placedOrdernotificationsContent=new LinkedList<>();
    }


    public String addOrder(Order order) {
        List<ProductReq> products=order.getProducts();
        // Check if the customer ID is registered
        if (customerService.getCustomer(order.getCustomerId()) == null) {
           return "Customer with ID " + order.getCustomerId() + " is not registered. Cannot add the order.";
        }
        for(ProductReq product:products){
            if(productService.getProductBySerialNumber(product.getSerialNum())==null){
              return"Product with serial number  " + product.getSerialNum() + "is not found";
            }
        }
        // Check if the order ID is not already registered
        if (getOrder(order.getOrderId()) != null) {
          return "Order with ID " + order.getOrderId() + " is already registered. Cannot add the order.";

        }
        // Add the order to the list
        orders.add(order);
        System.out.println("Order added successfully: " + order.getOrderId());
        AbstractNotificationChannel notification=new EmailChannel();
        //AbstractNotificationChannel notification=new SmsChannel(); if i want to use another channel
        notification.setOrder(order);
        String content = notification.createPlacedContent();
        placedOrdernotificationsContent.add(content);
        return content;
    }

    public Order getOrder(String orderId) {

        for (Order order: orders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    public List<Order> getAll() {
        return orders;
    }
    public List<Order> getAllShippingOrders() {
        return shippingOrders;
    }

    public boolean cancelOrder(String orderId) {
        Order order = getOrder(orderId);
        if (order != null) {
            LocalDateTime orderPlacementTime = order.getOrderPlacementTime();
            LocalDateTime currentTime = LocalDateTime.now();
            Duration duration = Duration.between(orderPlacementTime, currentTime);
            if (duration.compareTo(Duration.ofHours(2)) <= 0) {
//                if (order.isShipped()) {
//                    unshipOrder(orderId);
//                }
                orders.remove(order);
                Customer customer = customerService.getCustomer(order.getCustomerId());
                //customer.addBalance(order.getShippingFees());
                System.out.println("Order canceled: " + orderId);
                return true;
            } else {
                System.out.println("Cancellation period exceeded for order: " + orderId);
            }
        }
        return false;
    }
    public Order updateOrder(String orderId , Order updatedOrder) {
        orders.removeIf(order -> order.getOrderId().equals(orderId));
        orders.add(updatedOrder);
        return updatedOrder;
    }


    // =================================================
    // helper functions for the shipping logic
    // =================================================
    public void shippingSimpleOrder(Order order){
        order.setShipped(true);
        Customer customer = customerService.getCustomer(order.getCustomerId());
        customer.deductBalance(order.getShippingFees());

        for (ProductReq productReq : order.getProducts()) {
            Product product = productService.getProductBySerialNumber(productReq.getSerialNum());
            product.removeCount(productReq.getCount());
        }

    }
    public void unShippingSimpleOrder(Order order){
        order.setShipped(false);
        Customer customer = customerService.getCustomer(order.getCustomerId());
        customer.addBalance(order.getShippingFees());

        for (ProductReq productReq : order.getProducts()) {
            Product product = productService.getProductBySerialNumber(productReq.getSerialNum());
            product.addCount(productReq.getCount());
        }

    }
    public void shipSubOrders(List<SimpleOrder> subOrders){
        for (Order order : subOrders){
            shippingSimpleOrder(order);
        }
    }
    public void unshipSubOrders(List<SimpleOrder> subOrders){
        for (Order order : subOrders){
            unShippingSimpleOrder(order);
        }
    }


    // =================================================
    // the main functions for the shipping logic
    // =================================================
    public String  shipOrder(String orderId) {

        Order order = getOrder(orderId);
        if (order != null && !order.isShipped()) {

            shippingSimpleOrder(order);

            if (order.getSubOrders() != null) {
                List<SimpleOrder> subOrders = order.getSubOrders();
                shipSubOrders(subOrders);
            }

            shippingOrders.add(order);
            System.out.println("Shipping the order..." + orderId);
        }
        AbstractNotificationChannel notification=new EmailChannel();
        notification.setOrder(order);
        String content = notification.createShippeedContent();
        ShippedOrdernotificationContents.add(content);



        return content;
    }

    public String unshipOrder(String orderId) {
        Order order = getOrder(orderId);
        if (order != null && order.isShipped()) {
            unShippingSimpleOrder(order);

            if (order.getSubOrders() != null) {
                List<SimpleOrder> subOrders = order.getSubOrders();
                unshipSubOrders(subOrders);
            }

            shippingOrders.add(order);
            System.out.println("Shipping the order..." + orderId);
        }

        shippingOrders.removeIf(order1 -> order1.getOrderId().equals(orderId));


        return "cancel shipping the order..."+ orderId;
    }
    public Queue<String> getAllShippingNotifications(){
        return ShippedOrdernotificationContents;
    }
    public Queue<String> getAllPlacingNotifications(){
        return placedOrdernotificationsContent;
    }
}
