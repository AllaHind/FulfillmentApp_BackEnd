package com.fullfilmentApp.services;

import com.fullfilmentApp.models.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    public Customer findCustomer(String code);
    public ResponseEntity<?> saveCustomer(Customer customer);
    public ResponseEntity<?> updateCustomer(Customer customer,Long id);
    public ResponseEntity<?> deleteCustomer(Long id);

    public List<Customer> listCustomer();
;
}
