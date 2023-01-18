package com.fullfilmentApp.services;

import com.fullfilmentApp.Enum.OrderStatus;
import com.fullfilmentApp.Enum.PackagingStatus;
import com.fullfilmentApp.models.*;
import com.fullfilmentApp.models.Package;
import com.fullfilmentApp.repository.OrderItemRepository;
import com.fullfilmentApp.repository.OrderRepository;
import com.fullfilmentApp.repository.PackageRepository;
import com.fullfilmentApp.repository.PackagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
        packaging.setCode(generateRandomString(6));
        packaging.setStatus(PackagingStatus.PREPARED);
        Order order = orderRepository.findByCode(packaging.getOrder().getCode());
        packaging.setOrder(order);
        List<OrderItem> orderItems = order.getItems();
        List<Package> packages=new ArrayList<>();
        packagingRepository.save(packaging);
        for (OrderItem orderItem : orderItems) {
            Package pk = packagee(orderItem.getProduct().getWeight()*orderItem.getQuantity(), orderItem.getProduct().getVolume()*orderItem.getQuantity());
            pk.setOrderItem(orderItem);
            pk.setStatus("Not Available");
            pk.setPackaging(packaging);
            packages.add(pk);
            packageRepository.save(pk);
            int currentQuantity= orderItem.getProduct().getStockQuantity();
            int newQuantity=currentQuantity-orderItem.getQuantity();
               orderItem.getProduct().setStockQuantity(newQuantity);
        }
        order.setStatus(OrderStatus.READY_TO_SHIP);
        orderRepository.save(order);
        packaging.setPackageList(packages);
        return packaging;

    }
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random rand = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(rand.nextInt(characters.length())));
        }
        return result.toString();
    }

    public int numberOfPackages(int id) {
        return packagingRepository.numberOfPackages(id);
    }

    public Package packagee(double weight, double volume) {
        return packageRepository.packagee(weight, volume);
    }
}





