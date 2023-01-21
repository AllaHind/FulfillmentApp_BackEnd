package com.fullfilmentApp.controllers;


import com.fullfilmentApp.Enum.OrderStatus;
import com.fullfilmentApp.models.Order;
import com.fullfilmentApp.models.OrderItem;
import com.fullfilmentApp.services.OrderService;
import com.fullfilmentApp.services.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderServiceImpl orderServiceImp;

    @GetMapping("/{code}")
    public Order findOrder(@PathVariable("code") String code) {
        return orderService.findOrder(code);
    }
   @GetMapping("")
    public List<Order> listOrders() {
        return orderService.listOrders();
    }
     @PostMapping("")
    public ResponseEntity<?> saveOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }
   @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id) {
        return orderService.deleteOrder(id);
    }
   @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@RequestBody Order order,@PathVariable("id") Long id) {
        return orderService.updateOrder(order, id);
    }
    @GetMapping("orderItem/{code}")
    public List<OrderItem> findOrderItem(@PathVariable("code") String code) {
        return orderServiceImp.findOrderItem(code);
    }
   @GetMapping("/orderPending")
    public int orderPending() {
        return orderServiceImp.orderPending();
    }
    @PostMapping("/changeStatus/{sku}/{status}/{username}")
    public void changeStatus(@PathVariable("sku") String sku,@PathVariable("status") OrderStatus status,@PathVariable("username") String username) {
        orderServiceImp.changeStatus(sku, status,username);
    }
}
