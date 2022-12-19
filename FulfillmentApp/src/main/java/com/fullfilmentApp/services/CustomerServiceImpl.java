package com.fullfilmentApp.services;

import com.fullfilmentApp.models.Customer;
import com.fullfilmentApp.payload.response.MessageResponse;
import com.fullfilmentApp.repository.CustomerRepository;
import com.fullfilmentApp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer findCustomer(String code) {
        return customerRepository.findByCode(code);
    }

    public ResponseEntity<?> saveCustomer(Customer Customer) {
        if (findCustomer(Customer.getCode()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Customer already exists!"));
        }    if (fieldNullOrEmpty(Customer.getCode()) || fieldNullOrEmpty(Customer.getFullName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("All fields are required!"));
        } else customerRepository.save(Customer);
        return ResponseEntity
                .ok()
                .body(new MessageResponse("Customer has been registered successfully!"));
    }

    @Override
    public ResponseEntity<?> updateCustomer(Customer customer,Long id) {
        customer.setId(id);
        customerRepository.save(customer);
        return ResponseEntity.ok(new MessageResponse("Customer has been registered successfully!"));


    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteCustomer(Long id) {
        customerRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Customer has been deleted successfully!"));

    }

    @Override
    public List<Customer> listCustomer() {
        return customerRepository.findAll();
    }



    public boolean fieldNullOrEmpty(String field)
    {
        if(field==null || field.isEmpty())
        {
            return true;

        }
        else return false;
    }
    public boolean fieldZero(double field)
    {
        if(field==0.0)
        {
            return true;

        }
        else return false;
    }
}
