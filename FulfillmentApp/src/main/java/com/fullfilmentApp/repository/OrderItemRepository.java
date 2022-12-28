package com.fullfilmentApp.repository;

import com.fullfilmentApp.models.Category;
import com.fullfilmentApp.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

    List<OrderItem> findByOrderrCode(String  code);

}
