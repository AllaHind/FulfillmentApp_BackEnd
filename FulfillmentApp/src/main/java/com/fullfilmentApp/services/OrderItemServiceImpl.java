package com.fullfilmentApp.services;

import com.fullfilmentApp.models.Order;
import com.fullfilmentApp.models.OrderItem;
import com.fullfilmentApp.models.Product;
import com.fullfilmentApp.payload.response.MessageResponse;
import com.fullfilmentApp.repository.OrderItemRepository;
import com.fullfilmentApp.repository.OrderRepository;
import com.fullfilmentApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderItem> findOrderItem(String code) {
        return orderItemRepository.findByOrderrCode(code);
    }

    @Override
    public ResponseEntity<?> saveOrderItem(OrderItem orderItem) {
        Product product=productRepository.findBySku(orderItem.getProduct().getSku());
        orderItem.setProduct(product);
        orderItemRepository.save(orderItem);
        return ResponseEntity
                .ok()
                .body(new MessageResponse("OrderItem saved!"));
    }


    @Override
    @Transactional
    public ResponseEntity<?> deleteOrder(Long id) {
         orderItemRepository.deleteById(id);
        return ResponseEntity
                .ok()
                .body(new MessageResponse("OrderItem deleted!"));
    }


    @Override
    public ResponseEntity<?> updateOrder(OrderItem orderItem, Long id) {
        return null;
    }

    public  int save(Order order, List<OrderItem> orderItems) {
        // TODO Auto-generated method stub
        for (OrderItem d : orderItems) {
            d.setOrderr(order);
            saveOrderItem(d);


        }
        return 1;
    }


}
