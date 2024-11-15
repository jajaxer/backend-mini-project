package com.interview.customer_management_service.service;

import com.interview.customer_management_service.model.Customer;
import com.interview.customer_management_service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){

        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(UUID customerId) {
        return customerRepository.findById(customerId);
    }

    public void deleteCustomerById(UUID customerId) {
        customerRepository.deleteById(customerId);
    }

    public Customer updateCustomer( Customer customer) {
        return  customerRepository.save(customer);
    }
}
