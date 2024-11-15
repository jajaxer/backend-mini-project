package com.interview.customer_management_service.controller;

import com.interview.customer_management_service.model.Customer;
import com.interview.customer_management_service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("/{customerId}")
    public Optional<Customer> getCustomerById(@PathVariable UUID customerId) {
        return customerService.getCustomerById(customerId);
    }
    @PutMapping("{customerId}")
    public void updateCustomerById(@PathVariable UUID customerId, @RequestBody Customer customer) {
        customer.setCustomerId(customerId);
        customerService.updateCustomer( customer);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomerById(@PathVariable UUID customerId) {
        customerService.deleteCustomerById(customerId);
    }



}
