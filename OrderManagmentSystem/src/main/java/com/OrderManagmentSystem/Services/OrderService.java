package com.OrderManagmentSystem.Services;
import com.OrderManagmentSystem.Models.Customer;
import com.OrderManagmentSystem.Models.OrderModels.SimpleOrder;
import com.OrderManagmentSystem.Models.Product;
import com.OrderManagmentSystem.Models.OrderModels.Order;
import com.OrderManagmentSystem.Models.OrderModels.ProductReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class OrderService {


    private final List<Order> orders;
    private final List<Order> shippingOrders;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final AccountService accountService;

    public OrderService(ProductService productService, AccountService accountService) {
        this.orders = new ArrayList<>();
        this.shippingOrders = new ArrayList<>();
        this.productService = productService;
        this.accountService = accountService;
    }


    public Order addOrder(Order order){
        orders.add(order);
        return order;
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

    public boolean deleteOrder(String orderId) {
        return orders.removeIf(order -> order.getOrderId().equals(orderId));
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
        Customer customer = accountService.getCustomer(order.getCustomerId());
        customer.deductBalance(order.getShippingFees());

        for (ProductReq productReq : order.getProducts()) {
            Product product = productService.getProductBySerialNumber(productReq.getSerialNum());
            product.removeCount(productReq.getCount());
        }

    }
    public void unShippingSimpleOrder(Order order){
        order.setShipped(false);
        Customer customer = accountService.getCustomer(order.getCustomerId());
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
    public Order shipOrder(String orderId) {

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

        return order;
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


}
