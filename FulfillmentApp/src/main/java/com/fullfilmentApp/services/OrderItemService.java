package com.fullfilmentApp.services;

import com.fullfilmentApp.models.Order;
import com.fullfilmentApp.models.OrderItem;
import com.fullfilmentApp.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderItemService {
     List<OrderItem> findOrderItem(String code);
     ResponseEntity<?> saveOrderItem(OrderItem orderItem);
     ResponseEntity<?> deleteOrder (Long id);
    ResponseEntity<?> updateOrder(OrderItem orderItem,Long id);

    int save(Order order, List<OrderItem> orderItems);



}
