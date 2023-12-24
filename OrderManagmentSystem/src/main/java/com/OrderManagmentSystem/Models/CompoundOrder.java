package com.OrderManagmentSystem.Models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class CompoundOrder extends Order {

    private final List<Order> subOrders; // For compound orders

    public CompoundOrder() {
        this.subOrders = new ArrayList<>();
    }

    public void addSubOrder(Order subOrder) {
        subOrders.add(subOrder);
    }

    public List<Order> getSubOrders (){
        return subOrders;
    }

}
