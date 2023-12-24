package com.OrderManagmentSystem.Controllers;

import com.OrderManagmentSystem.Models.CompoundOrder;
import com.OrderManagmentSystem.Models.Order;
import com.OrderManagmentSystem.Models.SimpleOrder;
import com.OrderManagmentSystem.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public List<Order> getAllOrders(){
        return orderService.getAll();
    }

    @PostMapping("")
    public Order createOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable String orderId) {
        return orderService.getOrder(orderId);
    }

    @DeleteMapping("/{orderId}")
    public boolean deleteOrder(@PathVariable String orderId) {
        return orderService.deleteOrder(orderId);
    }

    @PutMapping("/{orderId}")
    public Order updateOrder(@PathVariable String orderId , @RequestBody SimpleOrder order){
        return orderService.updateOrder(orderId , order);

    }

    @PostMapping("/{orderId}/ship")
    public Order shipOrder(@PathVariable String orderId){
        return orderService.shipOrder(orderId);
    }


    }





