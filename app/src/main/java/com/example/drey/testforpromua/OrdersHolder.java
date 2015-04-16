package com.example.drey.testforpromua;

import com.example.drey.testforpromua.dataobjects.Order;

import java.util.List;

/**
 * Created by drey on 16.04.15.
 */
public interface OrdersHolder {
    void setOrders(List<Order> orders);
    List<Order> getOrders();
}
