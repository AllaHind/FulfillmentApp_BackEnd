package com.fullfilmentApp.controllers;

import com.fullfilmentApp.models.Customer;
import com.fullfilmentApp.repository.CustomerRepository;
import com.fullfilmentApp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/{code}")
    public Customer findCustomer(@PathVariable("code") String code) {
        return customerService.findCustomer(code);
    }

    @PostMapping("/")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable("id") Long id) {
        return customerService.updateCustomer(customer, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
        return customerService.deleteCustomer(id);
    }

    @GetMapping("")
    public List<Customer> listCustomer() {
        return customerService.listCustomer();
    }


}
