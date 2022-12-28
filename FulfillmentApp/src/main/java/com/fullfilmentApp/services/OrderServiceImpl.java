package com.fullfilmentApp.services;

import com.fullfilmentApp.Enum.OrderStatus;
import com.fullfilmentApp.Enum.ProductStatus;
import com.fullfilmentApp.models.Customer;
import com.fullfilmentApp.models.Order;
import com.fullfilmentApp.models.OrderItem;
import com.fullfilmentApp.models.Product;
import com.fullfilmentApp.payload.response.MessageResponse;
import com.fullfilmentApp.repository.CustomerRepository;
import com.fullfilmentApp.repository.OrderItemRepository;
import com.fullfilmentApp.repository.OrderRepository;
import com.fullfilmentApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Order findOrder(String code) {
        return orderRepository.findByCode(code);
    }

    @Override
    public List<Order> listOrders() {
        return orderRepository.findAll();
    }

    @Override
    public ResponseEntity<?> saveOrder(Order order) {
        double orderPrice=0;
         if(findOrder(order.getCode())!=null) {
             return ResponseEntity
                     .badRequest()
                     .body(new MessageResponse("Order already exists!"));
         }
         else
         {   Customer customer=customerRepository.findByCode(order.getCustomer().getCode());
             order.setCustomer(customer);
             order.setOrderDate(new Date());
             order.setStatus(OrderStatus.PENDING);
             List<OrderItem> orderItems=order.getItems();
             for(OrderItem orderItem:orderItems)
             {

                 Product product=productRepository.findBySku(orderItem.getProduct().getSku());
                 orderItem.setProduct(product);
                 orderPrice= orderPrice+(product.getSellingPrice()*orderItem.getQuantity());
                 System.out.print(orderPrice);
                 orderItem.setOrderr(order);
             }
             order.setTotalPrice(orderPrice);
             orderRepository.save(order);



         }
        return ResponseEntity
                .ok()
                .body(new MessageResponse("Order has been registered successfully!"));
    }

    @Override
    public ResponseEntity<?> deleteOrder(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateOrder(Order order, Long id) {
        return null;
    }

    public List<OrderItem> findOrderItem(String code) {
        return orderRepository.findOrderItem(code);
    }

    public int orderPending() {
        return orderRepository.orderPending();
    }
}
