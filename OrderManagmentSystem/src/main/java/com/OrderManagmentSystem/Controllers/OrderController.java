package com.OrderManagmentSystem.Controllers;

import com.OrderManagmentSystem.Models.Order;
import com.OrderManagmentSystem.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping("")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable String orderId) {
        return orderService.getOrder(orderId);
    }


}
