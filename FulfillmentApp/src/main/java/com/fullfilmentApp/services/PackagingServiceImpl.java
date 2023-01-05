package com.fullfilmentApp.services;

import com.fullfilmentApp.Enum.OrderStatus;
import com.fullfilmentApp.models.*;
import com.fullfilmentApp.models.Package;
import com.fullfilmentApp.repository.OrderItemRepository;
import com.fullfilmentApp.repository.OrderRepository;
import com.fullfilmentApp.repository.PackageRepository;
import com.fullfilmentApp.repository.PackagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PackagingServiceImpl {

    @Autowired
    private PackagingRepository packagingRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public Packaging savePackaging(Packaging packaging) {
        Order order = orderRepository.findByCode(packaging.getOrder().getCode());
        packaging.setOrder(order);
        List<OrderItem> orderItems = order.getItems();
        List<Package> packages=new ArrayList<>();
        packagingRepository.save(packaging);
        for (OrderItem orderItem : orderItems) {
            Package pk = packagee(orderItem.getProduct().getWeight()*orderItem.getQuantity(), orderItem.getProduct().getVolume()*orderItem.getQuantity());
            pk.setStatus("Not Available");
            pk.setOrderItem(orderItem);
            pk.setPackaging(packaging);
            packages.add(pk);
            packageRepository.save(pk);
            int currentQuantity= orderItem.getProduct().getStockQuantity();
               int newQuantity=currentQuantity-orderItem.getQuantity();
               orderItem.getProduct().setStockQuantity(newQuantity);

        }
        order.setStatus(OrderStatus.READY_TO_SHIP);
        order.setShippedAt(LocalDateTime.now());
        orderRepository.save(order);
        packaging.setPackageList(packages);
        return packaging;

    }

    public Package packagee(double weight, double volume) {
        return packageRepository.packagee(weight, volume);
    }
}





