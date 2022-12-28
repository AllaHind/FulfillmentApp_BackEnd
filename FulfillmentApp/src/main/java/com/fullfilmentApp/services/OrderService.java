package com.fullfilmentApp.services;

import com.fullfilmentApp.models.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    Order findOrder(String code);
    List<Order> listOrders();
    ResponseEntity<?> saveOrder(Order order);
    ResponseEntity<?> deleteOrder(Long id);
    ResponseEntity<?> updateOrder(Order order,Long id);


}
