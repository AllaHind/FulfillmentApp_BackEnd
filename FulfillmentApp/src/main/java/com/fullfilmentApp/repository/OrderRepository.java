package com.fullfilmentApp.repository;

import com.fullfilmentApp.Enum.OrderStatus;
import com.fullfilmentApp.models.Category;
import com.fullfilmentApp.models.Order;
import com.fullfilmentApp.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("select count(o) from Order o where o.status LIKE 'PENDING'")
    int orderPending();
    Order findByCode(String code);
    @Query("select DISTINCT oi from OrderItem oi , Order o where oi.orderr.id IN (SELECT o.id from Order where o.code LIKE :code)")

    List<OrderItem> findOrderItem(@PathVariable("code") String code);



}
