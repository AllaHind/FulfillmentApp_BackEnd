package com.fullfilmentApp.repository;

import com.fullfilmentApp.models.Customer;
import com.fullfilmentApp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findByCode(String code);


}
