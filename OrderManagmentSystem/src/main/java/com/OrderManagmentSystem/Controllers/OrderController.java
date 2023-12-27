package com.OrderManagmentSystem.Controllers;

import com.OrderManagmentSystem.Models.OrderModels.CompoundOrder;
import com.OrderManagmentSystem.Models.OrderModels.Order;
import com.OrderManagmentSystem.Models.OrderModels.SimpleOrder;
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

    @PostMapping("/simple")
    public Order createOrder(@RequestBody SimpleOrder order) {
        return orderService.addOrder(order);
    }
    @PostMapping("/compound")
    public Order createCompoundOrder(@RequestBody CompoundOrder order) {
        System.out.println(order);
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

    @PutMapping("/simple/{orderId}")
    public Order updateOrder(@PathVariable String orderId , @RequestBody SimpleOrder order){
        return orderService.updateOrder(orderId , order);

    }
    @PutMapping("/compound/{orderId}")
    public Order updateOrder(@PathVariable String orderId , @RequestBody CompoundOrder order){
        return orderService.updateOrder(orderId , order);

    }




    @PostMapping("/{orderId}/ship")
    public Order shipOrder(@PathVariable String orderId){
//        System.out.println(orderId);
        return orderService.shipOrder(orderId);
    }

    @PostMapping("/{orderId}/unship")
    public String unshipOrder(@PathVariable String orderId){
//        System.out.println(orderId);
        return orderService.unshipOrder(orderId);
    }

//
//    @PostMapping("compound/{orderId}/ship")
//    public Order shipCompound(@PathVariable String orderId){
//        return orderService.shipOrder(orderId);
//
//    }
//
//    @PostMapping("compound/{orderId}/unship")
//    public String unshipCompound(@PathVariable String orderId){
//        return orderService.unshipOrder(orderId);
//
//    }


    }





